package com.kapps.ordersyt.core.data.mapper

import com.kapps.ordersyt.core.data.local.entities.ProductEntity
import com.kapps.ordersyt.core.domain.model.Product

fun ProductEntity.toProduct():Product{
    return Product(
        productId = productId,
        name = name,
        pricePerAmount = pricePerAmount
    )
}