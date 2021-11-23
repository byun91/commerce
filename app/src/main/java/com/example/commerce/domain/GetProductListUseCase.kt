package com.example.commerce.domain

import com.example.commerce.data.repository.ProductRepository
import com.example.commerce.data.response.Product

class GetProductListUseCase (
    private val productRepository : ProductRepository
    ): UseCase {
    suspend operator fun invoke(index : Int = 1) : List<Product> {
        return productRepository.getProductList(index)
    }
}