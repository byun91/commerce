package com.example.commerce.data.repository

import com.example.commerce.data.network.ProductApiService
import com.example.commerce.data.response.Product
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class DefaultProductRepository(
    private val productApi: ProductApiService,
    private val ioDispatcher : CoroutineDispatcher
) : ProductRepository {
    override suspend fun getProductList(index : Int): List<Product> = withContext(ioDispatcher){
        val response = productApi.getProducts(index)
        return@withContext if (response.isSuccessful) {
            response.body()?.data?.product?: listOf()
        } else {
            listOf()
        }
    }
}