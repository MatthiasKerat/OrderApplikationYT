package com.kapps.ordersyt.order_feature.data.mapper

import com.kapps.ordersyt.core.data.local.entities.OrderEntity
import com.kapps.ordersyt.order_feature.domain.model.Order

fun Order.toOrderEntity(delivererName:String):OrderEntity{
    return OrderEntity(
        orderId = orderId,
        date = date,
        delivererTime = delivererTime,
        delivererName = delivererName
    )
}