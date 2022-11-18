package com.kapps.ordersyt.deliverer_feature.di


import com.kapps.ordersyt.deliverer_feature.data.repository.DelivererRepositoryImpl
import com.kapps.ordersyt.deliverer_feature.domain.repository.DelivererRepository
import com.kapps.ordersyt.core.data.local.DelivererDao
import com.kapps.ordersyt.core.data.local.ProductDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DelivererFeatureModule {

    @Provides
    @Singleton
    fun provideDelivererRepository(delivererDao: DelivererDao, productDao: ProductDao): DelivererRepository {
        return DelivererRepositoryImpl(delivererDao,productDao)
    }

}