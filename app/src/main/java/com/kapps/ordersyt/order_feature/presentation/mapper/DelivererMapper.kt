package com.kapps.ordersyt.order_feature.presentation.mapper

import com.kapps.ordersyt.core.domain.model.Deliverer
import com.kapps.ordersyt.order_feature.presentation.state.DelivererListItem

fun Deliverer.toDelivererListItem():DelivererListItem{
    return DelivererListItem(
        delivererId = delivererId,
        name = name
    )
}