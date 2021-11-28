package com.example.commerce.presentation.mylike

import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.view.isGone
import com.example.commerce.databinding.FragmentMylikeBinding
import com.example.commerce.presentation.BaseFragment
import com.example.commerce.presentation.adapter.ProductListAdapter
import com.example.commerce.presentation.detail.ProductDetailActivity
import com.example.commerce.presentation.list.ProductListState
import org.koin.android.ext.android.inject

internal class MyLikeFragment
    : BaseFragment<MyLikeViewModel, FragmentMylikeBinding>() {

    companion object{
        const val TAG = "MyLikeFragment"
    }

    private val adapter = ProductListAdapter(TAG)

    override val viewModel by inject<MyLikeViewModel>()
    override fun getViewBinding(): FragmentMylikeBinding
        = FragmentMylikeBinding.inflate(layoutInflater)

    override fun observeData() {
        viewModel.myLikeLiveData.observe(this){
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

    }

    private fun handleErrorState() {

    }

    private var lastPosition = 0

    private fun handleSuccessState(state: ProductListState.Success) = with(binding) {
        if (state.likeList.isNotEmpty())  {
            emptyResultTextView.isGone = true
            if (lastPosition > 0) {
                binding.recyclerView.scrollToPosition(lastPosition)
            }
            adapter.setProductList(state.likeList, state.likeList,
                productItemClickListener = { it, pos ->
                    lastPosition = pos
                    startProductDetailForResult.launch(
                        ProductDetailActivity.newIntent(requireContext(), it)
                    ) }
            ) {
                viewModel.likeProduct(it)
            }
        }
    }

    private val startProductDetailForResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            if (result.resultCode == ProductDetailActivity.PRODUCT_LIKE_RESULT_CODE) {
                wipeData()
            }
        }


    private fun handleLoadingState() {
    }


    override fun wipeData() {
        adapter.emptyProductList()
        viewModel.initData()
    }

    private fun initViews(binding: FragmentMylikeBinding) = with(binding){
        recyclerView.adapter = adapter
        reviewDesc.setOnClickListener {
            wipeData()
            viewModel.sortData(2)
        }
        reviewAsc.setOnClickListener {
            wipeData()
            viewModel.sortData(1)
        }
    }


}