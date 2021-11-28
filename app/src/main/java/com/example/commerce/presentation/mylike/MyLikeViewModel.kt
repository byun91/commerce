package com.example.commerce.presentation.mylike

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.commerce.data.response.Product
import com.example.commerce.domain.GetLocalProductListUsecase
import com.example.commerce.domain.LikeProductItemUseCase
import com.example.commerce.presentation.BaseViewModel
import com.example.commerce.presentation.list.ProductListState
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

internal class MyLikeViewModel(
    private val getLocalProductListUsecase: GetLocalProductListUsecase,
    private val likeProductItemUseCase:  LikeProductItemUseCase,
) : BaseViewModel() {

    private var _myLikeLiveData = MutableLiveData<ProductListState>(ProductListState.UnInitialized)
    val myLikeLiveData: LiveData<ProductListState> = _myLikeLiveData


    override fun fetchData(): Job = viewModelScope.launch {
        _myLikeLiveData.postValue(ProductListState.Loading)
        _myLikeLiveData.postValue(ProductListState.Success(listOf(),getLocalProductListUsecase()))
    }

    fun likeProduct(product: Product) = viewModelScope.launch {
        likeProductItemUseCase(product)
    }

    private var index = 0

    fun initData() {
        index = 0
        fetchData()
    }
}
