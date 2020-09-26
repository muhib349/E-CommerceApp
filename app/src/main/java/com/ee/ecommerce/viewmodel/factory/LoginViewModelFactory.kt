package com.ee.ecommerce.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ee.ecommerce.data.db.repositories.UserRepository
import com.ee.ecommerce.data.preferences.PreferenceProvider
import com.ee.ecommerce.viewmodel.LoginViewModel

@Suppress("UNCHECKED_CAST")
class LoginViewModelFactory(
    private val userRepository: UserRepository,
    private val preference: PreferenceProvider
): ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return LoginViewModel(userRepository, preference) as T
    }

}