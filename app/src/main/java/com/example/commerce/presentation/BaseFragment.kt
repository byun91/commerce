package com.example.commerce.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import kotlinx.coroutines.Job

internal abstract class BaseFragment<VM:BaseViewModel, VB:ViewBinding> : Fragment() {

    /*
    BaseViewModel, BaseBinding을 사용하는 이유

    1)
    Child Fragment 로부터 어떤 Child ViewModel, Child ViewBinding 타입을 지정 받더라도
    공통적으로 사용할 수 있도록

    2)
    BaseViewModel 에 있는 데이터나 기능들을 BaseFragment 에서 처리가능
    */


    abstract val viewModel : VM

    protected lateinit var binding : VB

    abstract fun getViewBinding() : VB

    private lateinit var fetchJob : Job

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = getViewBinding()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fetchJob = viewModel.fetchData()
        observeData()
    }

    abstract fun observeData()

    override fun onDestroyView() {
        super.onDestroyView()
        if (fetchJob.isActive) {
            fetchJob.cancel()
        }
    }
}