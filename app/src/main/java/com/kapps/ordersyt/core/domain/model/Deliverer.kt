package com.kapps.ordersyt.core.domain.model

import com.kapps.ordersyt.core.domain.SelectAndSortableByName

data class Deliverer(
    val delivererId:String,
    override val name:String,
    val products:List<Product>
):SelectAndSortableByName
