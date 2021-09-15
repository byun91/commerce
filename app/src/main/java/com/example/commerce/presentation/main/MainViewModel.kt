package com.example.commerce.presentation.main

import androidx.lifecycle.viewModelScope
import com.example.commerce.presentation.BaseViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

internal class MainViewModel : BaseViewModel() {
    override fun fetchData(): Job = viewModelScope.launch {
    }
}