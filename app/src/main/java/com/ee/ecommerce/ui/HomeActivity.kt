package com.ee.ecommerce.ui

import android.graphics.drawable.GradientDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.ee.ecommerce.R
import com.ee.ecommerce.data.Product
import com.ee.ecommerce.ui.adapters.ProductAdapter
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.cart_layout.*
import kotlinx.android.synthetic.main.cart_layout.view.*
import kotlinx.android.synthetic.main.item_product.*

class HomeActivity : AppCompatActivity() {

    lateinit var tvCartCount: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        //setting toolbar
        setSupportActionBar(my_toolbar)
        val actionbar = supportActionBar
        actionbar?.setDisplayHomeAsUpEnabled(true)

        //setting recycler view
        val productList = ArrayList<Product>()
        productList.add(Product(R.drawable.product_1,"Product one", 1220, 10))
        productList.add(Product(R.drawable.product_1,"Product two", 1220, 10))
        productList.add(Product(R.drawable.product_1,"Product three", 2000, 10))
        productList.add(Product(R.drawable.product_1,"Product four", 2220, 10))
        productList.add(Product(R.drawable.product_1,"Product five", 2220, 10))
        productList.add(Product(R.drawable.product_1,"Product six", 2220, 10))

        val mAdapter = ProductAdapter(productList, this)

        rv_products.layoutManager = GridLayoutManager(this,2)
        rv_products.adapter = mAdapter

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.cart_menu,menu)

        val menuItem: MenuItem?  = menu?.findItem(R.id.cart_menu_item)

        tvCartCount = menuItem!!.actionView.tv_cart_count
        menuItem!!.actionView.setOnClickListener(View.OnClickListener {
            //TODO: checkout here
        })

        return true
    }

}
