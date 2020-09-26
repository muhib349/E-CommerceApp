package com.ee.ecommerce.viewmodel

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ee.ecommerce.data.db.entities.User
import com.ee.ecommerce.data.db.repositories.UserRepository
import com.ee.ecommerce.utils.Coroutines
import java.lang.Exception

class SignupViewModel(private val userRepository: UserRepository) : ViewModel() {

    var name: String? = null
    var email: String? = null
    var password: String? = null
    var confirmedPassword: String? = null

    val errorMessage: MutableLiveData<String> = MutableLiveData()
    val successMessage: MutableLiveData<String> = MutableLiveData()


    fun onSubmitClick(view: View){
        Coroutines.main{
            if(name.isNullOrEmpty() || email.isNullOrEmpty() || password.isNullOrEmpty() || confirmedPassword.isNullOrEmpty() || password != confirmedPassword){
                errorMessage.value = "Please Enter Valid Input!"
                return@main
            }
            try {
                userRepository.insertUser(User(0,name!!,email!!,password!!))
                successMessage.value = "User registration successful"
            }catch (e: Exception){
                errorMessage.value = e.message
            }


        }
    }
}