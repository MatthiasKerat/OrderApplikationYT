package com.kapps.ordersyt.deliverer_feature.data.mapper

import com.kapps.ordersyt.core.data.local.entities.ProductEntity
import com.kapps.ordersyt.core.domain.model.Product


fun Product.toProductEntity(delivererId:String): ProductEntity {
    return ProductEntity(
        productId = productId,
        name = name,
        pricePerAmount = pricePerAmount,
        belongsToDeliverer = delivererId
    )
}