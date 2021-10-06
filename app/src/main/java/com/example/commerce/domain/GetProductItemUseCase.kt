package com.example.commerce.domain

import com.example.commerce.data.entity.product.ProductEntity
import com.example.commerce.data.repository.ProductRepository

internal class GetProductItemUseCase(
    private val productRepository : ProductRepository
) : UseCase{

    suspend operator fun invoke(productId : Long) : ProductEntity? {
        return productRepository.getProduct()
    }
}