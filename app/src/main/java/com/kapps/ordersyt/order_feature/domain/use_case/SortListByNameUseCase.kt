package com.kapps.ordersyt.order_feature.domain.use_case

import com.kapps.ordersyt.core.domain.SelectAndSortableByName

class SortListByNameUseCase {

    operator fun <T> invoke(list:List<T>):List<T> where T:SelectAndSortableByName{
        return list.sortedBy { item ->
            item.name
        }
    }

}