package com.example.commerce.di

import com.example.commerce.data.db.provideDB
import com.example.commerce.data.db.provideToDoDao
import com.example.commerce.data.network.buildOkHttpClient
import com.example.commerce.data.network.provideGsonConverterFactory
import com.example.commerce.data.network.provideProductApiService
import com.example.commerce.data.network.provideProductRetrofit
import com.example.commerce.data.repository.DefaultProductRepository
import com.example.commerce.data.repository.ProductRepository
import com.example.commerce.data.response.Product
import com.example.commerce.domain.GetLocalProductListUsecase
import com.example.commerce.domain.GetProductItemUseCase
import com.example.commerce.domain.GetProductListUseCase
import com.example.commerce.domain.LikeProductItemUseCase
import com.example.commerce.presentation.detail.ProductDetailViewModel
import com.example.commerce.presentation.list.ProductListViewModel
import com.example.commerce.presentation.main.MainViewModel
import com.example.commerce.presentation.mylike.MyLikeViewModel
import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {


     // ViewModel
     viewModel{MainViewModel()}
     viewModel{ProductListViewModel(getProductListUseCase = get(), get(),get())}
     viewModel{MyLikeViewModel(get(),get())}
     viewModel { (product : Product) -> ProductDetailViewModel(product,get(),get()) }

     // UseCase
     factory { GetProductListUseCase(productRepository = get()) }
     factory { GetProductItemUseCase(productRepository = get())  }
     factory { LikeProductItemUseCase(get()) }
     factory { GetLocalProductListUsecase(get())}

     // Coroutines Dispatcher
     single { Dispatchers.Main }
     single { Dispatchers.IO }

     //Repositories
     single<ProductRepository> {
          DefaultProductRepository(get(), ioDispatcher = get(),
               productDao = get())
     }

     single { provideGsonConverterFactory()}
     single { buildOkHttpClient() }
     single { provideProductRetrofit(okHttpClient = get(),gsonConverterFactory= get()) }
     single { provideProductApiService(retrofit = get()) }

     // Database
     single { provideDB(androidApplication())}
     single { provideToDoDao(get())}

}