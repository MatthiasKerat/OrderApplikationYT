package com.kapps.ordersyt.core.data.mapper

import com.kapps.ordersyt.core.data.local.entities.DelivererEntity
import com.kapps.ordersyt.core.domain.model.Deliverer
import com.kapps.ordersyt.core.domain.model.Product

fun DelivererEntity.toDeliver(products:List<Product>):Deliverer{
    return Deliverer(
        delivererId = delivererId,
        name = name,
        products = products
    )
}