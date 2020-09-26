package com.ee.ecommerce.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ee.ecommerce.data.db.repositories.UserRepository
import com.ee.ecommerce.viewmodel.SignupViewModel

@Suppress("UNCHECKED_CAST")
class SignupViewModelFactory(
    private val userRepository: UserRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return SignupViewModel(userRepository) as T
    }

}