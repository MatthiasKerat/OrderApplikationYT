package com.kapps.ordersyt.order_feature.presentation.mapper

import com.kapps.ordersyt.order_feature.domain.model.Order
import com.kapps.ordersyt.order_feature.presentation.state.OrderDetailListItem
import com.kapps.ordersyt.order_feature.presentation.state.OrderListItem

fun Order.toOrderDetailListItem():OrderDetailListItem{
    return OrderDetailListItem(
        orderId = orderId,
        delivererName = delivererName,
        orderDate = date,
        products = products.map { boughtProduct ->
            boughtProduct.toProductListItem()
        }
    )
}

fun Order.toOrderListItem():OrderListItem{
    return OrderListItem(
        orderId = orderId,
        delivererName = delivererName,
        orderDate = date,
        totalAmount = products.sumOf { (it.amount*it.pricePerAmount).toDouble() }
    )
}