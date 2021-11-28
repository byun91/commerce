package com.example.commerce.domain

import com.example.commerce.data.repository.ProductRepository

class GetLocalProductListUsecase (
    private val productRepository: ProductRepository) : UseCase{
        suspend operator fun invoke() = productRepository.getLocalProductList().map{it.toProduct() }
}