package com.example.commerce.data.network

import com.example.commerce.BuildConfig
import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

internal fun provideProductApiService(retrofit: Retrofit) : ProductApiService {
    return retrofit.create(ProductApiService::class.java)
}

internal fun provideProductRetrofit(
    okHttpClient: OkHttpClient,
    gsonConverterFactory: GsonConverterFactory
) : Retrofit = Retrofit.Builder()
        .baseUrl(Url.DOMAIN)
        .addConverterFactory(gsonConverterFactory)
        .client(okHttpClient)
        .build()


internal fun provideGsonConverterFactory() : GsonConverterFactory
= GsonConverterFactory.create(GsonBuilder()
    .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
    .create())

internal fun buildOkHttpClient() : OkHttpClient {
    val interceptor = HttpLoggingInterceptor()
    if (BuildConfig.DEBUG) {
        interceptor.level = HttpLoggingInterceptor.Level.BODY
    } else {
        interceptor.level = HttpLoggingInterceptor.Level.NONE
    }
    return OkHttpClient.Builder()
        .connectTimeout(5, TimeUnit.SECONDS)
        .addInterceptor(interceptor)
        .build()
}
