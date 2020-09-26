package com.ee.ecommerce.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.ee.ecommerce.R
import com.ee.ecommerce.data.db.AppDatabase
import com.ee.ecommerce.data.db.repositories.UserRepository
import com.ee.ecommerce.data.preferences.PreferenceProvider
import com.ee.ecommerce.databinding.ActivityLoginBinding
import com.ee.ecommerce.utils.makeToast
import com.ee.ecommerce.viewmodel.LoginViewModel
import com.ee.ecommerce.viewmodel.factory.LoginViewModelFactory
import com.ee.ecommerce.viewmodel.factory.SignupViewModelFactory
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val binding: ActivityLoginBinding = DataBindingUtil.setContentView(this,R.layout.activity_login)
        val userDao = AppDatabase.getInstance(this).userDao
        val userRepository = UserRepository(userDao)
        val preference = PreferenceProvider(this)
        val factory = LoginViewModelFactory(userRepository,preference)
        val viewModel = ViewModelProvider(this,factory).get(LoginViewModel::class.java)
        binding.vmLogin = viewModel

        tv_sign_up.setOnClickListener(View.OnClickListener {
            val intent = Intent(this,SignupActivity::class.java)
            startActivity(intent)
        })

        viewModel.errorEmail.observe(this, Observer {
            et_email.error = it
        })

        viewModel.errorPassword.observe(this, Observer {
            et_password.error = it
        })

        viewModel.failedMessage.observe(this, Observer {
            makeToast(it)
        })
        viewModel.successMessage.observe(this, Observer {
            makeToast(it)
            val intent = Intent(this,HomeActivity::class.java)
            startActivity(intent)
            finish()
        })

        viewModel.loggedInUser.observe(this, Observer {
            if(it != null){
                val intent = Intent(this,HomeActivity::class.java)
                startActivity(intent)
                finish()
            }
        })

    }
}
