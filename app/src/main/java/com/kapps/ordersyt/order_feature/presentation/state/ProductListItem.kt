package com.kapps.ordersyt.order_feature.presentation.state

data class ProductListItem(
    val id:String,
    val name:String,
    val pricePerAmount:Float,
    val selectedAmount:Int,
    val isExpanded:Boolean
)
