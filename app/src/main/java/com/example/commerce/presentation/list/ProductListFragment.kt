package com.example.commerce.presentation.list

import android.widget.Toast
import androidx.core.view.isGone
import com.example.commerce.databinding.FragmentProductListBinding
import com.example.commerce.presentation.BaseFragment
import com.example.commerce.presentation.adapter.ProductListAdapter
import org.koin.android.ext.android.inject

internal class ProductListFragment :
    BaseFragment<ProductListViewModel,FragmentProductListBinding>(){

    companion object{
        const val TAG = "ProductListFragment"
    }

    override val viewModel by inject<ProductListViewModel>()

    override fun getViewBinding(): FragmentProductListBinding
    = FragmentProductListBinding.inflate(layoutInflater)

    private val adapter = ProductListAdapter()

    override fun observeData()
    = viewModel.productListLiveData.observe(this){
        when (it) {
            is ProductListState.UnInitialized -> {
                initViews(binding)
            }
            is ProductListState.Loading -> {
                handleLoadingState()
            }
            is ProductListState.Success -> {
                handleSuccessState(it)
            }
            is ProductListState.Error -> {
                handleErrorState()
            }
        }
    }

    private fun handleSuccessState(state: ProductListState.Success) = with(binding) {
        refreshLayout.isRefreshing = false

        if (state.productList.isEmpty()) {
            emptyResultTextView.isGone = false
            recyclerView.isGone = true
        } else {
            emptyResultTextView.isGone = true
            recyclerView.isGone = false
            adapter.setProductList(state.productList) {
            }
        }
    }

    private fun handleLoadingState() = with(binding) {
        refreshLayout.isRefreshing = true
    }


    private fun handleErrorState() {
        Toast.makeText(requireContext(), "에러가 발생했습니다.", Toast.LENGTH_SHORT).show()
    }

    private fun initViews(binding: FragmentProductListBinding)
    = with(binding) {
        recyclerView.adapter = adapter
        refreshLayout.setOnRefreshListener{
            viewModel.fetchData()
        }

    }
}