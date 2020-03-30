package com.example.iconmobilesample.presentation

import android.os.Bundle
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.iconmobilesample.R
import com.iconmobile.sample.library.base.presentation.activity.BaseActivity
import kotlinx.android.synthetic.main.activity_nav_host.*

class NavHostActivity : BaseActivity() {

    override val layoutResId = R.layout.activity_nav_host

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupBottomNavigation()
    }

    private fun setupBottomNavigation() {
        val navController = navHostFragment.findNavController()
        bottomNav.setupWithNavController(navController)
    }
}
