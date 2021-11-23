package com.example.commerce.domain

import com.example.commerce.data.entity.product.ProductEntity
import com.example.commerce.data.repository.ProductRepository
import com.example.commerce.data.response.Product

internal class GetProductItemUseCase(
    private val productRepository : ProductRepository
) : UseCase{

    suspend operator fun invoke(productId : Long) : Product? {
        return productRepository.getProduct()
    }
}