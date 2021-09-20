package com.example.commerce.di

import com.example.commerce.data.network.buildOkHttpClient
import com.example.commerce.data.network.provideGsonConverterFactory
import com.example.commerce.data.network.provideProductApiService
import com.example.commerce.data.network.provideProductRetrofit
import com.example.commerce.data.repository.DefaultProductRepository
import com.example.commerce.data.repository.ProductRepository
import com.example.commerce.domain.GetProductItemUseCase
import com.example.commerce.presentation.list.ProductListViewModel
import com.example.commerce.presentation.main.MainViewModel
import com.example.commerce.presentation.profile.ProfileViewModel
import kotlinx.coroutines.Dispatchers
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {


     // ViewModel
     viewModel{MainViewModel()}
     viewModel{ProductListViewModel()}
     viewModel{ProfileViewModel()}

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