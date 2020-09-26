package com.ee.ecommerce.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ee.ecommerce.data.db.entities.Cart

@Dao
interface CartDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCart(cart: Cart)

    @Query("SELECT * FROM carts WHERE user_id =:userId")
    suspend fun getCartByUserId(userId: Int): List<Cart>
}