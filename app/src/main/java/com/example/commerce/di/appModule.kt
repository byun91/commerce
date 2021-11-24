package com.example.commerce.di

import com.example.commerce.data.network.buildOkHttpClient
import com.example.commerce.data.network.provideGsonConverterFactory
import com.example.commerce.data.network.provideProductApiService
import com.example.commerce.data.network.provideProductRetrofit
import com.example.commerce.data.repository.DefaultProductRepository
import com.example.commerce.data.repository.ProductRepository
import com.example.commerce.data.response.Product
import com.example.commerce.domain.GetProductItemUseCase
import com.example.commerce.domain.GetProductListUseCase
import com.example.commerce.presentation.detail.ProductDetailViewModel
import com.example.commerce.presentation.list.ProductListViewModel
import com.example.commerce.presentation.main.MainViewModel
import com.example.commerce.presentation.profile.ProfileViewModel
import kotlinx.coroutines.Dispatchers
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {


     // ViewModel
     viewModel{MainViewModel()}
     viewModel{ProductListViewModel(getProductListUseCase = get())}
     viewModel{ProfileViewModel()}
     viewModel { (product : Product) -> ProductDetailViewModel(product) }

     // UseCase
     factory { GetProductListUseCase(productRepository = get()) }
     factory { GetProductItemUseCase(productRepository = get())  }

     // Coroutines Dispatcher
     single { Dispatchers.Main }
     single { Dispatchers.IO }

     //Repositories
     single<ProductRepository> {
          DefaultProductRepository(get(), ioDispatcher = get()
          )
     }

     single { provideGsonConverterFactory()}
     single { buildOkHttpClient() }
     single { provideProductRetrofit(okHttpClient = get(),gsonConverterFactory= get()) }
     single { provideProductApiService(retrofit = get()) }

}