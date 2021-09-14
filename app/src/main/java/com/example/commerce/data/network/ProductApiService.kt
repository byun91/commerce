package com.example.commerce.data.network

import com.example.commerce.data.response.ProductResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ProductApiService {

    @GET("/product")
    suspend fun getProducts() : Response<ProductResponse>

    @GET("/product/{productId}")
    suspend fun getProduct(@Path("productId") productId:Long):
    Response<ProductResponse>
}