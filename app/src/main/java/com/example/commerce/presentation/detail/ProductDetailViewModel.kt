package com.example.commerce.presentation.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.commerce.data.response.Product
import com.example.commerce.presentation.BaseViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

internal class ProductDetailViewModel(private val product: Product) : BaseViewModel(){

    private var _productDescriptionLiveData = MutableLiveData<Product>()
    val productDescriptionLiveData: LiveData<Product> = _productDescriptionLiveData

    override fun fetchData(): Job = viewModelScope.launch {
       _productDescriptionLiveData.postValue(product)
    }
}