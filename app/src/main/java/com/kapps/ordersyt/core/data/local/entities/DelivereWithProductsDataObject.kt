package com.kapps.ordersyt.core.data.local.entities

import androidx.room.Embedded
import androidx.room.Relation

data class DelivererWithProductsDataObject(
    @Embedded val delivererEntity: DelivererEntity,
    @Relation(
        parentColumn = "delivererId",
        entityColumn = "belongsToDeliverer"
    )
    val products:List<ProductEntity>
)
