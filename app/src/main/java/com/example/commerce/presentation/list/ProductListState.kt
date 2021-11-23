package com.example.commerce.presentation.list

import com.example.commerce.data.response.Product

sealed class ProductListState {
    object UnInitialized : ProductListState()

    object Loading : ProductListState()

    data class Success(
        val productList : List<Product>,
        val index : Int
    ) : ProductListState()

    object Error : ProductListState()
}