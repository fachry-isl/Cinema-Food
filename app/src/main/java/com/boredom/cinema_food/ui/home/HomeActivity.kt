package com.boredom.cinema_food.ui.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.boredom.cinema_food.R
import com.boredom.cinema_food.databinding.ActivityHomeBinding
import com.google.android.material.snackbar.Snackbar


class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView = binding.bottomNavMain
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_main) as NavHostFragment
        val navController = navHostFragment.navController
        navView.setupWithNavController(navController)

        val extras = intent.extras
        if (extras != null) {
            val isSnackbarOrder = extras.getBoolean(EXTRA_SNACKBAR)
            val isSnackbarCheckout = extras.getBoolean(EXTRA_CHECKOUT_SNACKBAR)
            val isNavigateHistory = extras.getBoolean(EXTRA_CHECKOUT_HISTORY)
            showSnackbarOrder(isSnackbarOrder)
            showSnackbarCheckout(isSnackbarCheckout)
            navigateHistory(isNavigateHistory, navController)
        }

    }

    private fun navigateHistory(navigateHistory: Boolean, navController: NavController) {
        if (navigateHistory) {
            navController.navigate(R.id.navigation_history)
        }
    }

    private fun showSnackbarOrder(isSnackbar: Boolean) {
        if (isSnackbar) {
            Snackbar.make(
                binding.layoutHomeActivity,
                "Order is Added to Cart",
                Snackbar.LENGTH_SHORT
            ).show()
        }
    }

    private fun showSnackbarCheckout(isSnackbar: Boolean) {
        if (isSnackbar) {
            Snackbar.make(
                binding.layoutHomeActivity,
                "Order is being processed",
                Snackbar.LENGTH_SHORT
            ).show()
        }
    }

    companion object {
        const val EXTRA_SNACKBAR = "show_snackbar"
        const val EXTRA_CHECKOUT_SNACKBAR = "show_checkout_snackbar"
        const val EXTRA_CHECKOUT_HISTORY = "navigate_history"
    }

}