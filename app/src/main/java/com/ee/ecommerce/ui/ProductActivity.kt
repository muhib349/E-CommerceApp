package com.ee.ecommerce.ui

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.ee.ecommerce.R
import com.ee.ecommerce.data.Product
import com.ee.ecommerce.utils.Values
import com.ee.ecommerce.utils.makeToast
import kotlinx.android.synthetic.main.activity_product.*

class ProductActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product)

        //setting toolbar
        setSupportActionBar(my_toolbar)
        val actionbar = supportActionBar
        actionbar?.setDisplayHomeAsUpEnabled(true)

        val product: Product = intent.getSerializableExtra(Values.EXTRA_PRODUCT) as Product

        init(product)
    }

    private fun init(product: Product) {
        tv_title.text = product.title
        tv_price.text = product.price.toString()+"TK"
        btn_buy.setOnClickListener(View.OnClickListener {
            //TODO: on progress
            this.makeToast("working progress!!")
        })
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

}
