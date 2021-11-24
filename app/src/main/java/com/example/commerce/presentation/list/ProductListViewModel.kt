package com.example.commerce.presentation.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.commerce.domain.GetProductListUseCase
import com.example.commerce.presentation.BaseViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

internal class ProductListViewModel(
    private val getProductListUseCase: GetProductListUseCase
): BaseViewModel() {

    private var index = 0

    private var _productListStateLiveData
    = MutableLiveData<ProductListState>(ProductListState.UnInitialized)
    val productListLiveData: LiveData<ProductListState>
    = _productListStateLiveData

    override fun fetchData(): Job = viewModelScope.launch {
        setState(ProductListState.Loading) // isRefreshing = true
        setState(
            ProductListState.Success(getProductListUseCase(++index), index)
        )
    }

    private fun setState(state: ProductListState) {
        _productListStateLiveData.postValue(state)
    }

    fun initData() {
        index = 0
        fetchData()
    }
}