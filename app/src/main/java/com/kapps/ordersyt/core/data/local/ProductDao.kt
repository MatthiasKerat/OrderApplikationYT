package com.kapps.ordersyt.core.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.kapps.ordersyt.core.data.local.entities.ProductEntity

@Dao
interface ProductDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProduct(productEntity: ProductEntity)

    @Query("SELECT * FROM PRODUCTENTITY WHERE belongsToDeliverer = :delivererId")
    suspend fun getProductsForDeliverer(delivererId:String):List<ProductEntity>

}