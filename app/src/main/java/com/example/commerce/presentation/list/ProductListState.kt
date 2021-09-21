package com.example.commerce.presentation.list

import com.example.commerce.data.entity.product.ProductEntity

sealed class ProductListState {
    object UnInitialized : ProductListState()

    object Loading : ProductListState()

    data class Success(
        val productList : List<ProductEntity>
    ) : ProductListState()

    object Error : ProductListState()
}