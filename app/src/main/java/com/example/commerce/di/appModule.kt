package com.example.commerce.di

import com.example.commerce.data.network.buildOkHttpClient
import com.example.commerce.data.network.provideGsonConverterFactory
import com.example.commerce.data.network.provideProductApiService
import com.example.commerce.data.network.provideProductRetrofit
import com.example.commerce.data.repository.DefaultProductRepository
import com.example.commerce.data.repository.ProductRepository
import com.example.commerce.domain.GetProductItemUseCase
import kotlinx.coroutines.Dispatchers
import org.koin.dsl.module
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {

     // Coroutines Dispatcher
     single { Dispatchers.Main }
     single { Dispatchers.IO }

     // UseCases
     factory { GetProductItemUseCase(productRepository = get())  }

     //Repositories
     single<ProductRepository> {DefaultProductRepository(productApiService = get(), ioDispatcher = get())}

     single { provideGsonConverterFactory()}
     single { buildOkHttpClient() }
     single { provideProductRetrofit(okHttpClient = get(),gsonConverterFactory= get()) }
     single { provideProductApiService(retrofit = get()) }

}