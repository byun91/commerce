package com.example.commerce.data.repository

import com.example.commerce.data.entity.product.ProductEntity

interface ProductRepository {
    suspend fun getProductList() : List<ProductEntity>
    suspend fun getProduct() : ProductEntity
}