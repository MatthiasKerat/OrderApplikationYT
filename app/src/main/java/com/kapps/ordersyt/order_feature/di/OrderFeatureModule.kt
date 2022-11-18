package com.kapps.ordersyt.order_feature.di

import com.kapps.ordersyt.core.data.local.DelivererDao
import com.kapps.ordersyt.core.data.local.OrderDao
import com.kapps.ordersyt.core.data.local.ProductDao
import com.kapps.ordersyt.order_feature.data.repository.OrderRepositoryImpl
import com.kapps.ordersyt.order_feature.domain.repository.OrderRepository
import com.kapps.ordersyt.order_feature.domain.use_case.ConfirmOrderUseCase
import com.kapps.ordersyt.order_feature.domain.use_case.FilterListByNameUseCase
import com.kapps.ordersyt.order_feature.domain.use_case.SortListByNameUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object OrderFeatureModule {

    @Provides
    @Singleton
    fun provideOrderRepository(
        orderDao:OrderDao,
        delivererDao:DelivererDao,
        productDao: ProductDao
    ):OrderRepository{
        return OrderRepositoryImpl(orderDao,delivererDao,productDao)
    }

    @Provides
    @Singleton
    fun provideFilterListByNameUseCase():FilterListByNameUseCase{
        return FilterListByNameUseCase()
    }

    @Provides
    @Singleton
    fun provideSortListByNameUseCase():SortListByNameUseCase{
        return SortListByNameUseCase()
    }

    @Provides
    @Singleton
    fun provideConfirmOrderUseCase(orderRepository: OrderRepository):ConfirmOrderUseCase{
        return ConfirmOrderUseCase(orderRepository)
    }


}