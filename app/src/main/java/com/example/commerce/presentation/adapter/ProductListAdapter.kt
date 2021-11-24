package com.example.commerce.presentation.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.commerce.data.response.Product
import com.example.commerce.databinding.ViewholderProductItemBinding
import com.example.commerce.extensions.loadCenterCrop

@SuppressLint("NotifyDataSetChanged")
class ProductListAdapter
    : RecyclerView.Adapter<ProductListAdapter.ProductItemViewHolder>() {
    private var productList : List<Product> = listOf()
    private lateinit var productItemClickListener : (Product) -> Unit

    @SuppressLint("SetTextI18n")
    inner class ProductItemViewHolder(
        private val binding : ViewholderProductItemBinding,
        val productItemClickListener : (Product) -> Unit
    ) : RecyclerView.ViewHolder(binding.root){

        fun bindData(data : Product) = with(binding){
            productNameTextView.text = data.name
            productPriceTextView.text = data.description.price.toString() + "원"
            productGradeTextView.text = data.rate.toString()
            productImageView.loadCenterCrop(data.thumbnail, 8f)

        }
        fun bindViews(data : Product) {
            binding.root.setOnClickListener{
                productItemClickListener(data)
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductItemViewHolder {
        val view = ViewholderProductItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductItemViewHolder(view, productItemClickListener)
    }

    override fun onBindViewHolder(holder: ProductItemViewHolder, position: Int) {
        holder.bindData(productList[position])
        holder.bindViews(productList[position])
    }

    override fun getItemCount(): Int = productList.size

    fun setProductList(productList: List<Product>, productItemClickListener: (Product) -> Unit = { }) {
        this.productList += productList
        this.productItemClickListener = productItemClickListener
        notifyDataSetChanged()
    }

    fun emptyProductList() {
        this.productList = emptyList()
        notifyDataSetChanged()
    }

}