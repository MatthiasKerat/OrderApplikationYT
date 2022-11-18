package com.kapps.ordersyt.order_feature.domain.model

import com.kapps.ordersyt.core.domain.model.Product

data class Order(
    val orderId:String,
    val date:String,
    val delivererTime:String,
    val delivererName:String,
    val products:List<BoughtProduct>
)
