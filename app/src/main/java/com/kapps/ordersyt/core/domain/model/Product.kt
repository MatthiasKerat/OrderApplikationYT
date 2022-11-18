package com.kapps.ordersyt.core.domain.model

import com.kapps.ordersyt.core.domain.SelectAndSortableByName

data class Product(
    val productId:String,
    override val name:String,
    val pricePerAmount:Float
): SelectAndSortableByName
