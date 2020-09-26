package com.ee.ecommerce.data

import java.io.Serializable

data class Product(val code: String, val image: Int, val title: String, val price: Int, val quantity: Int) : Serializable {
}