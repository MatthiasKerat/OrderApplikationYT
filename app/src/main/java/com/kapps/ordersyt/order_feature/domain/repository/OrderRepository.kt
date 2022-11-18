package com.kapps.ordersyt.order_feature.domain.repository

import com.kapps.ordersyt.core.domain.model.Deliverer
import com.kapps.ordersyt.core.domain.model.Product
import com.kapps.ordersyt.order_feature.domain.model.Order

interface OrderRepository {

    suspend fun insertOrder(order: Order)

    suspend fun getOrders():List<Order>

    suspend fun getDeliverers():List<Deliverer>

    suspend fun getProductsForDeliverer(delivererId:String):List<Product>

    suspend fun getDelivererNameById(delivererId: String):String
}