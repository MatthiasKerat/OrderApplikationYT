package com.kapps.ordersyt.order_feature.data.repository

import com.kapps.ordersyt.core.data.local.DelivererDao
import com.kapps.ordersyt.core.data.local.OrderDao
import com.kapps.ordersyt.core.data.local.ProductDao
import com.kapps.ordersyt.core.data.local.entities.OrderProductEntity
import com.kapps.ordersyt.core.data.mapper.toDeliver
import com.kapps.ordersyt.core.data.mapper.toProduct
import com.kapps.ordersyt.core.domain.model.Deliverer
import com.kapps.ordersyt.core.domain.model.Product
import com.kapps.ordersyt.order_feature.data.mapper.toOrder
import com.kapps.ordersyt.order_feature.data.mapper.toOrderEntity
import com.kapps.ordersyt.order_feature.domain.model.Order
import com.kapps.ordersyt.order_feature.domain.repository.OrderRepository
import javax.inject.Inject

class OrderRepositoryImpl @Inject constructor(
    private val orderDao:OrderDao,
    private val delivererDao: DelivererDao,
    private val productDao: ProductDao
):OrderRepository {
    override suspend fun insertOrder(order: Order) {
        orderDao.insertOrder(order.toOrderEntity(order.delivererName))
        val orderProductEntities = order.products.map { boughtProduct ->
            OrderProductEntity(order.orderId,boughtProduct.productId, boughtProduct.amount)
        }
        orderDao.insertOrderProductEntities(orderProductEntities)
    }

    override suspend fun getOrders(): List<Order> {
        return orderDao.getOrderWithProducts().map {
            it.toOrder()
        }
    }

    override suspend fun getDeliverers(): List<Deliverer> {
        return delivererDao.getDeliverers().map {
            it.delivererEntity.toDeliver(
                it.products.map { productEntity ->
                    productEntity.toProduct()
                }
            )
        }
    }

    override suspend fun getProductsForDeliverer(delivererId: String): List<Product> {
        return productDao.getProductsForDeliverer(delivererId).map { productEntity ->
            productEntity.toProduct()
        }
    }

    override suspend fun getDelivererNameById(delivererId: String): String {
        return delivererDao.getDelivererNameById(delivererId)
    }
}