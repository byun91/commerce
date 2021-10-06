package com.example.commerce.presentation.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.commerce.data.entity.product.ProductEntity
import com.example.commerce.domain.GetProductItemUseCase
import com.example.commerce.presentation.BaseViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

internal class ProductDetailViewModel(
    private val productId: Long,
    private val getProductItemUseCase: GetProductItemUseCase
    ) : BaseViewModel(){

    private var _productDetailState =
        MutableLiveData<ProductDetailState>(ProductDetailState.UnInitialized)
    val productDetailState : LiveData<ProductDetailState> = _productDetailState

    private lateinit var productEntity: ProductEntity

    override fun fetchData(): Job = viewModelScope.launch {
        setState(ProductDetailState.Loading)
        getProductItemUseCase(productId)?.let { product ->
            productEntity = product
            setState(
                ProductDetailState.Success(product)
            )
        } ?: kotlin.run {
            setState(ProductDetailState.Error)
        }
    }

    private fun setState(state: ProductDetailState) {
        _productDetailState.postValue(state)
    }




}