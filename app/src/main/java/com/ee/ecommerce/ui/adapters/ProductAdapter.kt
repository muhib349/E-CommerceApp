package com.ee.ecommerce.ui.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.ee.ecommerce.R
import com.ee.ecommerce.data.Product
import com.ee.ecommerce.databinding.RecyclerviewProductBinding
import com.ee.ecommerce.ui.ProductActivity
import com.ee.ecommerce.ui.listener.HomeActivityListener
import com.ee.ecommerce.utils.Values
import kotlinx.android.synthetic.main.recyclerview_product.view.*

class ProductAdapter(
    private val productList: List<Product>,
    private val listener: HomeActivityListener
) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    var cartItemCount = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : ProductViewHolder{
        val inflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<RecyclerviewProductBinding>(inflater,R.layout.recyclerview_product,parent,false)
        return ProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val currentProduct = productList[position]

        holder.recyclerviewProductBinding.product = currentProduct

        holder.recyclerviewProductBinding.btnAddToCart.setOnClickListener(View.OnClickListener {
            listener.onProductItemClick(holder.recyclerviewProductBinding.btnAddToCart, currentProduct)
        })
        holder.recyclerviewProductBinding.ivProductImg.setOnClickListener(View.OnClickListener {
            listener.onProductItemClick(holder.recyclerviewProductBinding.ivProductImg, currentProduct)
        })
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    class ProductViewHolder(val recyclerviewProductBinding: RecyclerviewProductBinding): RecyclerView.ViewHolder(recyclerviewProductBinding.root) {

    }
}