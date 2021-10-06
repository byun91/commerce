package com.example.commerce.presentation.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.core.view.isGone
import androidx.core.view.isVisible
import com.example.commerce.databinding.ActivityProductDetailBinding
import com.example.commerce.extensions.load
import com.example.commerce.extensions.loadCenterCrop
import com.example.commerce.extensions.toast
import com.example.commerce.presentation.BaseActivity
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

internal class ProductDetailActivity : BaseActivity<ProductDetailViewModel, ActivityProductDetailBinding>() {


    companion object {

        const val PRODUCT_ID_KEY = "PRODUCT_ID_KEY"

        const val PRODUCT_ORDERED_RESULT_CODE = 99

        fun newIntent(context: Context, productId: Long) =
            Intent(context, ProductDetailActivity::class.java).apply {
                putExtra(PRODUCT_ID_KEY, productId)
            }

    }

    override val viewModel by inject<ProductDetailViewModel>{
        parametersOf(
            intent.getLongExtra(PRODUCT_ID_KEY,-1)
        )
    }

    override fun getViewBinding(): ActivityProductDetailBinding
    = ActivityProductDetailBinding.inflate(layoutInflater)

    override fun observeData() = viewModel.productDetailState.observe(this) {
        when (it) {
            is ProductDetailState.UnInitialized -> initViews()
            is ProductDetailState.Loading -> handleLoading()
            is ProductDetailState.Success -> handleSuccess(it)
            is ProductDetailState.Error -> handleError()
            is ProductDetailState.Order -> handleOrder()
        }
    }

    private fun handleLoading() = with(binding) {
        progressBar.isVisible = true
    }

    private fun handleSuccess(state : ProductDetailState.Success) = with(binding) {
        progressBar.isGone = true
        val product = state.productEntity
        productCategoryTextView.text = product.productType
        productImageView.loadCenterCrop(product.productImage, 8f)
        productPriceTextView.text = "${product.productPrice}원"
        introductionImageView.load(state.productEntity.productImage)
    }

    private fun initViews() = with(binding){
        setSupportActionBar(toolbar)
    }


    private fun handleError() {
        toast("제품 정보를 불러올 수 없습니다.")
        finish()
    }

    private fun handleOrder() {
        setResult(PRODUCT_ORDERED_RESULT_CODE)
        toast("성공적으로 주문이 완료되었습니다.")
        finish()
    }


}














