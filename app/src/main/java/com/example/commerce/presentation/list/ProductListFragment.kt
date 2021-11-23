package com.example.commerce.presentation.list

import android.widget.LinearLayout
import android.widget.Toast
import androidx.core.view.isGone
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.commerce.databinding.FragmentProductListBinding
import com.example.commerce.extensions.toast
import com.example.commerce.presentation.BaseFragment
import com.example.commerce.presentation.adapter.ProductListAdapter
import com.example.commerce.presentation.detail.ProductDetailActivity
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

        if (state.productList.isNotEmpty())  {
            emptyResultTextView.isGone = true
            recyclerView.isGone = false
            adapter.setProductList(state.productList) {
                startActivity(
                    ProductDetailActivity.newIntent(requireContext(), it.id)
                )
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
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val lastPos = (recyclerView.layoutManager as LinearLayoutManager).findLastCompletelyVisibleItemPosition()
                recyclerView.adapter?.itemCount?.let { totalCount ->
                    if (lastPos == totalCount - 1) {
                        viewModel.fetchData()
                    }
                }


            }

        })
        refreshLayout.setOnRefreshListener{
            adapter.emptyProductList()
            viewModel.initData()
        }
    }
}