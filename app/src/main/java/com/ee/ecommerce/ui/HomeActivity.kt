package com.ee.ecommerce.ui

import android.content.Intent
import android.graphics.drawable.GradientDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.ee.ecommerce.R
import com.ee.ecommerce.data.Product
import com.ee.ecommerce.data.db.AppDatabase
import com.ee.ecommerce.data.db.dao.CartDao
import com.ee.ecommerce.data.db.repositories.CartRepository
import com.ee.ecommerce.data.preferences.PreferenceProvider
import com.ee.ecommerce.ui.adapters.ProductAdapter
import com.ee.ecommerce.ui.listener.HomeActivityListener
import com.ee.ecommerce.utils.Values
import com.ee.ecommerce.utils.makeToast
import com.ee.ecommerce.viewmodel.HomeViewModel
import com.ee.ecommerce.viewmodel.factory.HomeViewModelFactory
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.cart_layout.*
import kotlinx.android.synthetic.main.cart_layout.view.*
import kotlinx.android.synthetic.main.recyclerview_product.*

class HomeActivity : AppCompatActivity(), HomeActivityListener {

    lateinit var tvCartCount: TextView
    lateinit var viewModel: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        //setting toolbar
        setSupportActionBar(my_toolbar)
        val actionbar = supportActionBar
        actionbar?.setDisplayHomeAsUpEnabled(true)

        val catDao = AppDatabase.getInstance(this).cartDao
        val repository = CartRepository(catDao)
        val preference = PreferenceProvider(this)
        val factory = HomeViewModelFactory(repository, preference)
        viewModel = ViewModelProvider(this,factory).get(HomeViewModel::class.java)


        //setting recycler view
        val productList = ArrayList<Product>()
        productList.add(Product("1001",R.drawable.product_1,"Product one", 1220, 10))
        productList.add(Product("1002",R.drawable.product_1,"Product two", 1220, 10))
        productList.add(Product("1003",R.drawable.product_1,"Product three", 2000, 10))
        productList.add(Product("1004",R.drawable.product_1,"Product four", 2220, 10))
        productList.add(Product("1005",R.drawable.product_1,"Product five", 2220, 10))
        productList.add(Product("1006",R.drawable.product_1,"Product six", 2220, 10))

        rv_products.layoutManager = GridLayoutManager(this,2)
        val mAdapter = ProductAdapter(productList, this)
        rv_products.adapter = mAdapter


        viewModel.cartList.observe(this, Observer {
            tvCartCount.text = it.size.toString()
        })
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

    override fun onProductItemClick(view: View, product: Product) {
        when(view.id){
            R.id.iv_product_img ->{
                val intent = Intent(this,ProductActivity::class.java)
                intent.putExtra(Values.EXTRA_PRODUCT, product)
                startActivity(intent)
            }

            R.id.btn_add_to_cart ->{
                viewModel.saveCartItem(product)
            }
        }
    }

}
