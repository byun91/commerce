package com.example.commerce.data.network

import com.example.commerce.data.response.ProductResponse
import com.example.commerce.data.response.ProductsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ProductApiService {

    @GET("/api/product")
    suspend fun getProducts() : Response<ProductsResponse>

    @GET("/product/{productId}")
    suspend fun getProduct(@Path("productId") productId:Long):
    Response<ProductResponse>
}