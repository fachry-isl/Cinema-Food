package com.boredom.cinema_food.ui.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
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

        val extras = intent.extras
        if (extras != null) {
            val isSnackbar = extras.getBoolean(EXTRA_SNACKBAR)
            showSnackbar(isSnackbar)
        }

        val navView = binding.bottomNavMain
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_main) as NavHostFragment
        val navController = navHostFragment.navController
        navView.setupWithNavController(navController)
    }

    private fun showSnackbar(isSnackbar: Boolean) {
        if (isSnackbar) {
            Snackbar.make(
                binding.layoutHomeActivity,
                "Order is Added to Cart",
                Snackbar.LENGTH_SHORT
            ).show()
        }
    }

    companion object {
        const val EXTRA_SNACKBAR = "show_snackbar"
    }

}