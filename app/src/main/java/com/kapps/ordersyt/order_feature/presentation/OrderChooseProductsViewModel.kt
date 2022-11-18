package com.kapps.ordersyt.order_feature.presentation

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kapps.ordersyt.core.domain.model.Product
import com.kapps.ordersyt.order_feature.domain.repository.OrderRepository
import com.kapps.ordersyt.order_feature.domain.use_case.ConfirmOrderUseCase
import com.kapps.ordersyt.order_feature.domain.use_case.FilterListByNameUseCase
import com.kapps.ordersyt.order_feature.domain.use_case.SortListByNameUseCase
import com.kapps.ordersyt.order_feature.presentation.mapper.toBoughtProduct
import com.kapps.ordersyt.order_feature.presentation.mapper.toProductListItem
import com.kapps.ordersyt.order_feature.presentation.state.ProductListItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OrderChooseProductsViewModel @Inject constructor(
    private val orderRepository: OrderRepository,
    private val filterListByNameUseCase: FilterListByNameUseCase,
    private val sortListByNameUseCase: SortListByNameUseCase,
    private val confirmOrderUseCase: ConfirmOrderUseCase
):ViewModel() {

    private lateinit var products:List<Product>

    private lateinit var delivererId:String

    var productsToShow = mutableStateListOf<ProductListItem>()
        private set

    var selectedProducts by mutableStateOf<List<ProductListItem>>(emptyList())
        private set

    var productSearchQuery by mutableStateOf("")
        private set

    var isCheckoutDialogShown by mutableStateOf(false)
        private set

    fun initProductList(delivererId:String){
        viewModelScope.launch {
            products = orderRepository.getProductsForDeliverer(delivererId)
            this@OrderChooseProductsViewModel.delivererId = delivererId
            setupProductsToShow()
        }
    }

    fun onProductSearchQueryChange(newName:String){
        productSearchQuery = newName
        setupProductsToShow()
    }

    private fun setupProductsToShow(){
        productsToShow = filterListByNameUseCase(
            sortListByNameUseCase(products),productSearchQuery
        ).map { product ->
            product.toProductListItem()
        }.map { productListItem ->
            val selectedItem = selectedProducts.firstOrNull{
                it.id == productListItem.id
            }
            if(selectedItem!=null){
                productListItem.copy(selectedAmount = selectedItem.selectedAmount)
            }else{
                productListItem
            }
        }.toMutableStateList()
    }

    fun onListItemClick(productId:String){
        val index = getIndexOfProduct(productId)
        if(index<0){
            return
        }
        productsToShow[index] = productsToShow[index].copy(
            isExpanded = !productsToShow[index].isExpanded
        )
    }

    fun onPlusClick(productId: String){
        val index = getIndexOfProduct(productId)
        if(index<0){
            return
        }

        val currentSelectionAmount = productsToShow[index].selectedAmount

        productsToShow[index] = productsToShow[index].copy(
            selectedAmount = currentSelectionAmount +1
        )
        if(currentSelectionAmount == 0){
            selectedProducts += productsToShow[index]
        }else{
            selectedProducts = selectedProducts.map {
                if(it.id == productId){
                    it.copy(selectedAmount = it.selectedAmount+1)
                }else{
                    it
                }
            }
        }
    }

    fun onMinusClick(productId: String){
        val index = getIndexOfProduct(productId)

        if(index<0){
            return
        }

        val currentSelectionAmount = productsToShow[index].selectedAmount

        if(currentSelectionAmount == 0){
            return
        }
        if(currentSelectionAmount == 1){
            selectedProducts = selectedProducts.toMutableList().apply {
                removeAll { it.id == productsToShow[index].id }
            }
        }
        productsToShow[index] = productsToShow[index].copy(
            selectedAmount = currentSelectionAmount - 1
        )
    }

    private fun getIndexOfProduct(productId: String):Int{
        return productsToShow.indexOfFirst { productListItem ->
            productListItem.id == productId
        }
    }

    fun onCheckoutClick(){
        isCheckoutDialogShown = true
    }

    fun onDismissCheckoutDialog(){
        isCheckoutDialogShown = false
    }

    fun onBuy(){
        confirmOrderUseCase(
            selectedProducts.map {
                it.toBoughtProduct()
            },
            delivererId = delivererId
        )
    }

}