package com.ee.ecommerce.ui.listener

import android.view.View
import com.ee.ecommerce.data.Product

interface HomeActivityListener {
    fun onProductItemClick(view: View, product: Product)
}