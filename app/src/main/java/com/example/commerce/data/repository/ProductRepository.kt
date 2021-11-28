package com.example.commerce.data.repository

import com.example.commerce.data.db.ProductEntity
import com.example.commerce.data.response.Product

interface ProductRepository {
    suspend fun getProductList(index : Int) : List<Product>

    suspend fun getLocalProductList(): List<ProductEntity>

    suspend fun likeProductItem(ProductItem: ProductEntity) : Boolean
}