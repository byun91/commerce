package com.example.commerce.domain

import com.example.commerce.data.entity.product.ProductEntity
import com.example.commerce.data.repository.ProductRepository

class GetProductListUseCase (
    private val productRepository : ProductRepository
    ): UseCase {
    suspend operator fun invoke() : List<ProductEntity> {
        return productRepository.getProductList()
    }
}