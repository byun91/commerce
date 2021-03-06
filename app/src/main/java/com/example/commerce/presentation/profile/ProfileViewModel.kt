package com.example.commerce.presentation.profile

import androidx.lifecycle.viewModelScope
import com.example.commerce.presentation.BaseViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

internal class ProfileViewModel : BaseViewModel() {
    override fun fetchData(): Job = viewModelScope.launch {

    }
}
