package com.ee.ecommerce.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.ee.ecommerce.R
import com.ee.ecommerce.data.db.AppDatabase
import com.ee.ecommerce.data.db.repositories.UserRepository
import com.ee.ecommerce.databinding.ActivitySignupBinding
import com.ee.ecommerce.utils.makeToast
import com.ee.ecommerce.viewmodel.SignupViewModel
import com.ee.ecommerce.viewmodel.factory.SignupViewModelFactory

class SignupActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
        val binding: ActivitySignupBinding = DataBindingUtil.setContentView(this, R.layout.activity_signup)
        val userDao = AppDatabase.getInstance(this).userDao
        val userRepository = UserRepository(userDao)
        val factory = SignupViewModelFactory(userRepository)
        val viewmodel = ViewModelProvider(this,factory).get(SignupViewModel::class.java)
        binding.vmSignup = viewmodel


        setSupportActionBar(binding.myToolbar)
        val actionbar = supportActionBar
        actionbar?.setDisplayHomeAsUpEnabled(true)

        viewmodel.errorMessage.observe(this, Observer {
            makeToast(it)
        })

        viewmodel.successMessage.observe(this, Observer {
            makeToast(it)
            val intent = Intent(this,LoginActivity::class.java)
            startActivity(intent)
            finish()
        })


    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
