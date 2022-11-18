package com.kapps.ordersyt.order_feature.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kapps.ordersyt.core.domain.model.Deliverer
import com.kapps.ordersyt.order_feature.domain.repository.OrderRepository
import com.kapps.ordersyt.order_feature.domain.use_case.FilterListByNameUseCase
import com.kapps.ordersyt.order_feature.domain.use_case.SortListByNameUseCase
import com.kapps.ordersyt.order_feature.presentation.mapper.toDelivererListItem
import com.kapps.ordersyt.order_feature.presentation.state.DelivererListItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OrderChooseDelivererViewModel @Inject constructor(
    private val orderRepository: OrderRepository,
    private val sortListByNameUseCase: SortListByNameUseCase,
    private val filterListByNameUseCase: FilterListByNameUseCase
):ViewModel() {

    private lateinit var deliverers:List<Deliverer>

    var deliverersToShow by mutableStateOf<List<DelivererListItem>>(emptyList())
        private set

    var delivererSearchQuery by mutableStateOf("")
        private set

    init {
        viewModelScope.launch {
            deliverers = orderRepository.getDeliverers()
            setupDeliverersToShow()
        }
    }

    fun onSearchQueryChange(newValue:String){
        delivererSearchQuery = newValue
        setupDeliverersToShow()
    }

    private fun setupDeliverersToShow(){
        deliverersToShow = filterListByNameUseCase(
            sortListByNameUseCase(deliverers),delivererSearchQuery
        ).map { deliverer ->
            deliverer.toDelivererListItem()
        }
    }

}