package com.ee.ecommerce.data

import java.io.Serializable

data class Product(val image: Int, val title: String, val price: Int, val quantity: Int) : Serializable {
}