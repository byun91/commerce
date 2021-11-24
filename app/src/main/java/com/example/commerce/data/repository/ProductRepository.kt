package com.example.commerce.data.repository

import com.example.commerce.data.response.Product

interface ProductRepository {
    suspend fun getProductList(index : Int) : List<Product>
}