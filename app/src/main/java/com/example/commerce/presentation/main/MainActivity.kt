package com.example.commerce.presentation.main

import android.os.Bundle
import android.view.MenuItem
import com.example.commerce.databinding.ActivityMainBinding
import com.example.commerce.presentation.BaseActivity
import com.google.android.material.navigation.NavigationBarView
import org.koin.android.ext.android.inject

internal class MainActivity : BaseActivity<MainViewModel, ActivityMainBinding>() ,
    NavigationBarView.OnItemSelectedListener{

    override val viewModel by inject<MainViewModel>()
    override fun getViewBinding(): ActivityMainBinding =
        ActivityMainBinding.inflate(layoutInflater)

    private fun initViews() = with(binding){
        bottomNav.setOnItemSelectedListener(this@MainActivity)

    }
    override fun observeData() {
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        return false
    }

}