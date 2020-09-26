package com.ee.ecommerce.viewmodel

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ee.ecommerce.data.db.entities.User
import com.ee.ecommerce.data.db.repositories.UserRepository
import com.ee.ecommerce.data.preferences.PreferenceProvider
import com.ee.ecommerce.utils.Coroutines
import com.ee.ecommerce.utils.Values

class LoginViewModel(
    private val repository: UserRepository,
    private val prefs: PreferenceProvider
) : ViewModel() {
    var email: String? = null
    var password: String? = null


    val errorEmail: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    val errorPassword: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
    val failedMessage: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
    val successMessage: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    val loggedInUser: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }


    init {
        loggedInUser.value = prefs.getSavedItem(Values.USER)
    }

    fun onLoginButtonClick(view: View){
        if(email.isNullOrEmpty()){
            errorEmail.value = "Enter your email address"
            return
        }else if(password.isNullOrEmpty()){
            errorPassword.value = "Enter your valid password"
            return
        }
        Coroutines.main {
            val user: User? = repository.verifyUser(email!!,password!!)
            if(user != null ){
                prefs.saveItem(Values.USER, user.toString())
                successMessage.value = "User login successful"

            }else{
                failedMessage.value = "Invalid email or password"
            }
        }
    }

}