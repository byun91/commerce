package com.example.commerce.presentation.detail

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import com.example.commerce.data.response.Product
import com.example.commerce.databinding.ActivityProductDetailBinding
import com.example.commerce.extensions.load
import com.example.commerce.presentation.BaseActivity
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

@SuppressLint("SetTextI18n")
internal class ProductDetailActivity : BaseActivity<ProductDetailViewModel, ActivityProductDetailBinding>() {


    companion object {

        const val PRODUCT_LIKE_RESULT_CODE = 99
        const val PRODUCT_KEY = "PRODUCT_KEY"

        fun newIntent(context: Context, product: Product) =
            Intent(context, ProductDetailActivity::class.java).apply {
                putExtra(PRODUCT_KEY, product)
            }

    }

    override val viewModel by inject<ProductDetailViewModel>{
        parametersOf(intent.extras?.getSerializable(PRODUCT_KEY) as Product)
    }

    override fun getViewBinding(): ActivityProductDetailBinding
    = ActivityProductDetailBinding.inflate(layoutInflater)

    private fun initViews(product : Product) = with(binding){
        productImageView.load(product.description.imagePath)
        productPriceTextView.text = "" + product.description.price + "원"
        productContentTextView.text = "<"+product.name + ">\n" +
        "평점:" + product.rate + "\n" +product.description.subject
        productLikeImageView.setOnClickListener {
            productLikeImageView.isSelected = !productLikeImageView.isSelected
            viewModel.likeProduct(product)
            setResult(PRODUCT_LIKE_RESULT_CODE)
        }
    }

    override fun observeData() {
        viewModel.productDescriptionLiveData.observe(this) {
            initViews(it)
        }
        viewModel.isLikeLiveData.observe(this){
            it?.let {
                binding.productLikeImageView.isSelected = it
            }
        }
    }

}














