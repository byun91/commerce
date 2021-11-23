package com.example.commerce.data.response

import com.google.gson.annotations.SerializedName

data class ResponseData1(
    val code: Int,
    val data: Data,
    val msg: String
)

data class Data(
    val product: List<Product>,
    @SerializedName("totalCount") val totalCount: Int
)

data class Product(
    val description: Description,
    val id: Int,
    val name: String,
    val rate: Double,
    val thumbnail: String
){

}

data class Description(
    val imagePath: String,
    val price: Int,
    val subject: String
)