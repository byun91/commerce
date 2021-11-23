package com.example.commerce.data.network

import com.example.commerce.data.response.ResponseData1
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ProductApiService {

    @GET("/App/json/{index}.json")
    suspend fun getProducts(@Path("index") id : Int) : Response<ResponseData1>

}