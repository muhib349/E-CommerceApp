package com.ee.ecommerce.data.db.repositories

import androidx.lifecycle.LiveData
import com.ee.ecommerce.data.db.dao.CartDao
import com.ee.ecommerce.data.db.entities.Cart

class CartRepository(private val cartDao: CartDao) {
    suspend fun insertCart(cart: Cart) = cartDao.insertCart(cart)

    suspend fun getCartListByUser(userId: Int) : List<Cart> = cartDao.getCartByUserId(userId)
}