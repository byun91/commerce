package com.example.commerce.presentation.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.commerce.data.response.Product
import com.example.commerce.databinding.ViewholderProductItemBinding
import com.example.commerce.extensions.loadCenterCrop

@SuppressLint("NotifyDataSetChanged")
class ProductListAdapter(val tag : String)
    : RecyclerView.Adapter<ProductListAdapter.ProductItemViewHolder>() {
    private var productList : List<Product> = listOf()
    private var likeList : List<Product> = listOf()
    private lateinit var productItemClickListener : (Product, Int) -> Unit
    private lateinit var productLikeClickListener : (Product) -> Unit

    @SuppressLint("SetTextI18n")
    inner class ProductItemViewHolder(
        private val binding : ViewholderProductItemBinding,
        val productItemClickListener : (Product, Int) -> Unit
    ) : RecyclerView.ViewHolder(binding.root){

        fun bindData(data : Product) = with(binding){
            productNameTextView.text = data.name
            productPriceTextView.text = data.description.price.toString() + "원"
            productGradeTextView.text = data.rate.toString()
            productImageView.loadCenterCrop(data.thumbnail, 8f)
            productLikeImageView.isSelected = likeList.contains(data)
        }
        fun bindViews(data : Product, position: Int) = with(binding){
            root.setOnClickListener{
                productItemClickListener(data, position)
            }
            productLikeImageView.setOnClickListener {
                productLikeImageView.isSelected = !productLikeImageView.isSelected
                productLikeClickListener(data)
                if (tag == "MyLikeFragment" && !productLikeImageView.isSelected) {
                    binding.root.visibility = View.GONE
                    notifyDataSetChanged()
                }
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductItemViewHolder {
        val view = ViewholderProductItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductItemViewHolder(view, productItemClickListener)
    }

    override fun onBindViewHolder(holder: ProductItemViewHolder, position: Int) {
        holder.bindData(productList[position])
        holder.bindViews(productList[position],position)
    }

    override fun getItemCount(): Int = productList.size

    fun setProductList(
        productList: List<Product>, likeList: List<Product>,
        productItemClickListener: (Product, Int) -> Unit,
        productLikeClickListener: (Product) -> Unit = { }
    ) {
        this.productList += productList
        this.likeList = likeList
        this.productItemClickListener = productItemClickListener
        this.productLikeClickListener = productLikeClickListener
        notifyDataSetChanged()
    }

    fun emptyProductList() {
        this.productList = emptyList()
        notifyDataSetChanged()
    }

}