package com.kapps.ordersyt.deliverer_feature.data.repository

import com.kapps.ordersyt.core.data.local.DelivererDao
import com.kapps.ordersyt.core.data.local.ProductDao
import com.kapps.ordersyt.core.domain.model.Deliverer
import com.kapps.ordersyt.core.domain.model.Product
import com.kapps.ordersyt.deliverer_feature.data.mapper.toDelivererEntity
import com.kapps.ordersyt.deliverer_feature.data.mapper.toProductEntity
import com.kapps.ordersyt.deliverer_feature.domain.repository.DelivererRepository
import javax.inject.Inject

class DelivererRepositoryImpl @Inject constructor(
    private val delivererDao: DelivererDao,
    private val productDao: ProductDao
): DelivererRepository {

    override suspend fun insertDeliverers(list: List<Deliverer>) {
        list.forEach { deliverer ->
            delivererDao.insertDeliverer(deliverer.toDelivererEntity())
            insertProducts(deliverer.products, deliverer.delivererId)
        }
    }

    override suspend fun insertProducts(list: List<Product>, delivererId:String) {
        list.forEach { product ->
            productDao.insertProduct(product.toProductEntity(delivererId))
        }
    }

}