package com.example.commerce.domain

import com.example.commerce.data.repository.ProductRepository

class GetLocalProductListUsecase (
    private val productRepository: ProductRepository) : UseCase{
        suspend operator fun invoke(orderby : Int = 0) = productRepository.getLocalProductList(orderby).map{it.toProduct() }
}