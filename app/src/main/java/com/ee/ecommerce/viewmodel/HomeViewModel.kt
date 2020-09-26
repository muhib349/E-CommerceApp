package com.ee.ecommerce.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ee.ecommerce.data.Product
import com.ee.ecommerce.data.db.entities.Cart
import com.ee.ecommerce.data.db.entities.User
import com.ee.ecommerce.data.db.repositories.CartRepository
import com.ee.ecommerce.data.preferences.PreferenceProvider
import com.ee.ecommerce.utils.Coroutines
import com.ee.ecommerce.utils.Values

class HomeViewModel(
    private val repository: CartRepository,
    private val prefs: PreferenceProvider
) : ViewModel(){

    private val _cartList = MutableLiveData<List<Cart>>()

    val cartList: LiveData<List<Cart>>
    get() = _cartList

    init {
        Coroutines.main {
            _cartList.value = repository.getCartListByUser(1)
            return@main
        }
    }


    //val user: String? = prefs.getSavedItem(Values.USER)
    fun saveCartItem(product: Product){
        val cart = Cart(0, 1,product.code, 1)

        Coroutines.main {
            repository.insertCart(cart)
            _cartList.value = repository.getCartListByUser(1)
            return@main
        }
    }
}