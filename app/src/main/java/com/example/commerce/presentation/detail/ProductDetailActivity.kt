package com.example.commerce.presentation.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.example.commerce.databinding.ActivityProductDetailBinding
import com.example.commerce.presentation.BaseActivity
import org.koin.android.ext.android.inject

internal class ProductDetailActivity : BaseActivity<ProductDetailViewModel, ActivityProductDetailBinding>() {


    companion object {

        const val PRODUCT_ID_KEY = "PRODUCT_ID_KEY"

        const val PRODUCT_ORDERED_RESULT_CODE = 99

        fun newIntent(context: Context, productId: Long) =
            Intent(context, ProductDetailActivity::class.java).apply {
                putExtra(PRODUCT_ID_KEY, productId)
            }

    }

    override val viewModel by inject<ProductDetailViewModel>()

    override fun getViewBinding(): ActivityProductDetailBinding
    = ActivityProductDetailBinding.inflate(layoutInflater)

    override fun observeData() {

    }
}