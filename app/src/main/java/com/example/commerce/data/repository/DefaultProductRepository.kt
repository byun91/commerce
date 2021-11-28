package com.example.commerce.data.repository

import com.example.commerce.data.db.ProductDao
import com.example.commerce.data.db.ProductEntity
import com.example.commerce.data.network.ProductApiService
import com.example.commerce.data.response.Product
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class DefaultProductRepository(
    private val productApi: ProductApiService,
    private val ioDispatcher : CoroutineDispatcher,
    private val productDao : ProductDao
) : ProductRepository {
    override suspend fun getProductList(index : Int): List<Product> = withContext(ioDispatcher){
        val response = productApi.getProducts(index)
        return@withContext if (response.isSuccessful) {
            response.body()?.data?.product?: listOf()
        } else {
            listOf()
        }
    }

    override suspend fun getLocalProductList(): List<ProductEntity> = withContext(ioDispatcher) {
        productDao.getAll()
    }

    override suspend fun likeProductItem(productItem: ProductEntity): Boolean = withContext(ioDispatcher) {
       productDao.getById(productItem.id)?.let {
           productDao.delete(productItem.id)
           false
       }?: kotlin.run {
           productDao.insert(productItem)
           true
       }
    }
}