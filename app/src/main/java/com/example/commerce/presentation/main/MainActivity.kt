package com.example.commerce.presentation.main

import android.os.Bundle
import android.view.MenuItem
import androidx.fragment.app.Fragment
import com.example.commerce.R
import com.example.commerce.databinding.ActivityMainBinding
import com.example.commerce.presentation.BaseActivity
import com.example.commerce.presentation.list.ProductListFragment
import com.example.commerce.presentation.profile.ProfileFragment
import com.google.android.material.navigation.NavigationBarView
import org.koin.android.ext.android.inject

internal class MainActivity : BaseActivity<MainViewModel, ActivityMainBinding>() ,
    NavigationBarView.OnItemSelectedListener {

    override val viewModel by inject<MainViewModel>()
    override fun getViewBinding(): ActivityMainBinding =
        ActivityMainBinding.inflate(layoutInflater)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViews()
    }

    private fun initViews() = with(binding){
        bottomNav.setOnItemSelectedListener(this@MainActivity)
        showFragment(ProductListFragment(), ProductListFragment.TAG)
    }
    override fun observeData() {
    }

    private fun showFragment(fragment: Fragment, tag: String) {
        val findFragment = supportFragmentManager.findFragmentByTag(tag)
        supportFragmentManager.fragments.forEach { fm ->
            supportFragmentManager.beginTransaction().hide(fm).commit()
        }
        findFragment?.let{
            supportFragmentManager.beginTransaction().show(it).commit()
        } ?: kotlin.run {
            supportFragmentManager.beginTransaction().add(R.id.fragmentContainer, fragment, tag)
                .commitAllowingStateLoss()
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.menu_products ->{
                showFragment(ProductListFragment(), ProductListFragment.TAG)

                true
            }
            R.id.menu_profile ->{
                showFragment(ProfileFragment(), ProfileFragment.TAG)
                true
            }
            else -> false

        }
    }

}