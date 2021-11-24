package com.example.commerce.domain

import com.example.commerce.data.repository.ProductRepository
import com.example.commerce.data.response.Product

internal class GetProductItemUseCase(
    private val productRepository : ProductRepository
) : UseCase{

    suspend operator fun invoke(productId : Int) : Product? {
        return null
    }
}