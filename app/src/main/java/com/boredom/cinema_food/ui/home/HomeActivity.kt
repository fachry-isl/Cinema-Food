package com.boredom.cinema_food.ui.home

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.boredom.cinema_food.R
import com.boredom.cinema_food.databinding.ActivityHomeBinding
import com.firebase.ui.auth.AuthUI
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth


class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding

    private lateinit var mFirebaseAuth: FirebaseAuth

    private var mAuthStateListener: FirebaseAuth.AuthStateListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize Firebase Components
        mFirebaseAuth = FirebaseAuth.getInstance()

        // Setup authentication
        setupAuthStateListener()

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

    private fun setupAuthStateListener() {
        mAuthStateListener = FirebaseAuth.AuthStateListener { firebaseAuth ->
            val user = firebaseAuth.currentUser
            if (user != null) {
                // User is signed in
            } else {
                // User is signed out
                startActivityForResult(
                    AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setTheme(R.style.FirebaseLoginTheme)
                        .setLogo(R.drawable.moviecaffe_logo)
                        .setIsSmartLockEnabled(false)
                        .setAvailableProviders(
                            listOf(
                                AuthUI.IdpConfig.GoogleBuilder().build(),
                                AuthUI.IdpConfig.EmailBuilder().build()
                            )
                        )
                        .build(),
                    RC_SIGN_IN
                )
            }
        }
    }

    override fun onResume() {
        super.onResume()
        mFirebaseAuth.addAuthStateListener(mAuthStateListener as FirebaseAuth.AuthStateListener)
    }

    override fun onPause() {
        super.onPause()
        if (mAuthStateListener != null) {
            mFirebaseAuth.removeAuthStateListener(mAuthStateListener as FirebaseAuth.AuthStateListener)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK) {
            Toast.makeText(this, "You're signed in", Toast.LENGTH_SHORT).show()
        } else if (resultCode == RESULT_CANCELED) {
            Toast.makeText(this, "You're signed out", Toast.LENGTH_SHORT).show()
            finish()
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
        const val RC_SIGN_IN = 1
    }

}