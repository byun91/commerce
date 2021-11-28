package com.example.commerce.domain

import com.example.commerce.data.repository.ProductRepository
import com.example.commerce.data.response.Product

class LikeProductItemUseCase(
    private val productRepository: ProductRepository
) : UseCase{
    suspend operator fun invoke(product : Product) : Boolean
    =  productRepository.likeProductItem(product.toEntity())
}