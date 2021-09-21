package com.example.commerce.data.repository

import com.example.commerce.data.entity.product.ProductEntity
import com.example.commerce.data.network.ProductApiService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class DefaultProductRepository(
    private val productApi: ProductApiService,
    private val ioDispatcher : CoroutineDispatcher
) : ProductRepository {
    override suspend fun getProductList(): List<ProductEntity> = withContext(ioDispatcher){
        val response = productApi.getProducts()
        return@withContext if (response.isSuccessful) {
            response.body()?.items?.map { it.toEntity()}?: listOf()
        } else {
            listOf()
        }
    }

    override suspend fun getProduct(): ProductEntity = withContext(ioDispatcher){
        TODO("Not yet implemented")
    }
}