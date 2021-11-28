package com.example.commerce.presentation.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.commerce.data.response.Product
import com.example.commerce.domain.GetLocalProductListUsecase
import com.example.commerce.domain.GetProductListUseCase
import com.example.commerce.domain.LikeProductItemUseCase
import com.example.commerce.presentation.BaseViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

internal class ProductDetailViewModel(private val product: Product,
    private val likeProductItemUseCase: LikeProductItemUseCase,
    private val getLocalProductListUsecase: GetLocalProductListUsecase) : BaseViewModel(){

    private var _productDescriptionLiveData = MutableLiveData<Product>()
    val productDescriptionLiveData: LiveData<Product> = _productDescriptionLiveData

    private var _isLikeLiveData = MutableLiveData<Boolean?>()
    val isLikeLiveData : LiveData<Boolean?> = _isLikeLiveData

    override fun fetchData(): Job = viewModelScope.launch {
       _productDescriptionLiveData.postValue(product)
        _isLikeLiveData.postValue(getLocalProductListUsecase().contains(product))

    }

    fun likeProduct(product: Product) : Job = viewModelScope.launch {
        _isLikeLiveData.postValue(likeProductItemUseCase(product))
    }


}