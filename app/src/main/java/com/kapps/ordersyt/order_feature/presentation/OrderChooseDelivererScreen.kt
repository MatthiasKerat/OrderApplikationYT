package com.kapps.ordersyt.order_feature.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.kapps.ordersyt.core.presentation.ScreenRoutes
import com.kapps.ordersyt.order_feature.presentation.components.DelivererUiListItem
import com.kapps.ordersyt.ui.theme.gray
import com.kapps.ordersyt.ui.theme.orange
import com.kapps.ordersyt.ui.theme.white

@Composable
fun OrderChooseDelivererScreen(
    navController: NavController,
    orderChooseDelivererViewModel: OrderChooseDelivererViewModel = hiltViewModel()
) {

    val scaffoldState = rememberScaffoldState()

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Deliverer selection",
                        color = white
                    )
                },
                backgroundColor = orange
            )
        }
    ){
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(gray)
                .padding(15.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            TextField(
                value = orderChooseDelivererViewModel.delivererSearchQuery,
                onValueChange = {
                    orderChooseDelivererViewModel.onSearchQueryChange(it)
                },
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = white,
                    textColor = gray,
                    cursorColor = orange,
                    focusedLabelColor = orange,
                    focusedIndicatorColor = orange
                ),
                label = {
                    Text("Search deliverer")
                },
                maxLines = 1
            )
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(10.dp),
                modifier = Modifier
                    .padding(top = 20.dp)
            ){
                items(
                    orderChooseDelivererViewModel.deliverersToShow,
                    key = {delivererListItem ->
                        delivererListItem.delivererId
                    }
                ){
                    DelivererUiListItem(
                        it,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(10.dp))
                            .border(1.dp,color = white, RoundedCornerShape(10.dp))
                            .clickable {
                                navController.navigate(ScreenRoutes.OrderChooseProductsScreen.route+"/${it.delivererId}")
                            }
                            .padding(15.dp)

                    )
                }
            }
        }
    }

}