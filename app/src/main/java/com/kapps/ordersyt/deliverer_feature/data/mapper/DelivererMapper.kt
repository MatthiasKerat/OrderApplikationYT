package com.kapps.ordersyt.deliverer_feature.data.mapper

import com.kapps.ordersyt.core.data.local.entities.DelivererEntity
import com.kapps.ordersyt.core.domain.model.Deliverer

fun Deliverer.toDelivererEntity():DelivererEntity{
    return DelivererEntity(
        delivererId = delivererId,
        name = name
    )
}