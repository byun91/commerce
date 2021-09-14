package com.example.commerce.data.repository

import com.example.commerce.data.entity.product.ProductEntity
import com.example.commerce.data.network.ProductApiService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class DefaultProductRepository(
    private val productApiService: ProductApiService,
    private val ioDispatcher : CoroutineDispatcher
) : ProductRepository {
    override suspend fun getProductList(): List<ProductEntity> = withContext(ioDispatcher){
        TODO("Not yet implemented")
    }

    override suspend fun getProduct(): ProductEntity = withContext(ioDispatcher){
        TODO("Not yet implemented")
    }
}