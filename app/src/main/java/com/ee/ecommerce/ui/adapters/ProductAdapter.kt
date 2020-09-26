package com.ee.ecommerce.ui.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ee.ecommerce.R
import com.ee.ecommerce.data.Product
import com.ee.ecommerce.ui.ProductActivity
import com.ee.ecommerce.utils.Values
import kotlinx.android.synthetic.main.item_product.view.*

class ProductAdapter(private val productList: List<Product>,
private val context: Context) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    var cartItemCount = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_product,parent,false)
        return ProductViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val currentItem = productList[position]

        holder.ivProductImg.setImageResource(currentItem.image)
        holder.tvProductPrice.text = currentItem.price.toString()
        holder.tvProductTitle.text = currentItem.title
        holder.btnAddToCart.setOnClickListener(View.OnClickListener {
            cartItemCount++
            //TODO: save product in cart table
        })

        holder.ivProductImg.setOnClickListener(View.OnClickListener {
            val intent = Intent(context,ProductActivity::class.java)
            intent.putExtra(Values.EXTRA_PRODUCT, productList[position])
            context.startActivity(intent)
        })
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    class ProductViewHolder(item: View): RecyclerView.ViewHolder(item) {
        val ivProductImg = item.iv_product_img
        val tvProductPrice = item.tv_price
        val tvProductTitle = item.tv_name
        val btnAddToCart = item.btn_add_to_cart

    }
}