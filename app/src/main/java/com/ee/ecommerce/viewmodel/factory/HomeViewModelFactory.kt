package com.ee.ecommerce.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ee.ecommerce.data.db.repositories.CartRepository
import com.ee.ecommerce.data.preferences.PreferenceProvider
import com.ee.ecommerce.viewmodel.HomeViewModel

@Suppress("UNCHECKED_CAST")
class HomeViewModelFactory (
    private val cartRepository: CartRepository,
    private val prefs: PreferenceProvider
): ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return HomeViewModel(cartRepository, prefs) as T
    }

}