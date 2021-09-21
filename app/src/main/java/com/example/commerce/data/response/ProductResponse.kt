package com.example.commerce.data.response

import com.example.commerce.data.entity.product.ProductEntity
import com.google.gson.annotations.SerializedName
import java.util.*

data class ProductResponse(
    val id: String,
    @SerializedName("createdAt") val createdAt: Date,
    val name: String,
    val price: Int,
    val image: String,
    @SerializedName("introductionImage") val introductionImage:String,
    val type: String,
){
    fun toEntity() : ProductEntity =
        ProductEntity(
            id = id.toLong(),
             createdAt = createdAt,
            productName = name,
            productPrice = price.toDouble().toInt(),
            productImage = image,
            productType = type,
            productIntroductionImage = introductionImage

        )
}

