package com.example.commerce.presentation.list

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

            }
            is ProductListState.Success -> {
            }
            is ProductListState.Error -> {

            }
        }
    }

    private fun initViews(binding: FragmentProductListBinding)
    = with(binding) {
        recyclerView.adapter = adapter
        refreshLayout.setOnRefreshListener{
            viewModel.fetchData()
        }

    }
}