package com.kapps.ordersyt.core.util

import com.kapps.ordersyt.core.domain.model.Deliverer
import com.kapps.ordersyt.core.domain.model.Product

object DummyData {

    val deliverers = listOf(
        Deliverer(
            "1",
            "Paper Factory Ltd",
            listOf(
                Product("1","Notebook Big",1.45f),
                Product("2","Notebook Medium",1.25f),
                Product("3","Notebook Small",1.05f),
                Product("4","Printer Paper 50x",2.55f),
                Product("5","Printer Paper 100x",4.55f),
            )
        ),
        Deliverer(
            "2",
            "School Stuff provider",
            listOf(
                Product("6","Pencil",1.00f),
                Product("7","Scissor",2.19f),
                Product("8","Tablet",149.00f),
                Product("9","School Bag",21.50f),
                Product("10","Pencil Case",2.69f),
            )
        ),
        Deliverer(
            "3",
            "Computer Hardware Store",
            listOf(
                Product("11","Keyboard",41.00f),
                Product("12","Mouse",22.19f),
                Product("13","Intel Processor i7",249.00f),
                Product("14","2x 8GB RAM",49.10f),
                Product("15","2x 16GB RAM",89.00f),
                Product("16","2x 32GB RAM",129.50f),
                Product("17","2x 64GB RAM",189.00f),
            )
        )
    )

}