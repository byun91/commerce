package com.example.commerce.presentation.detail

import com.example.commerce.data.response.Product

sealed class ProductDetailState {
    object UnInitialized: ProductDetailState()
    object Loading: ProductDetailState()
    data class Success(
        val productEntity: Product
    ): ProductDetailState()
    object Order: ProductDetailState()
    object Error: ProductDetailState()

}