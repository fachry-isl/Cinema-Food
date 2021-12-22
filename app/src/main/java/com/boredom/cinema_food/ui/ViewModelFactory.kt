package com.boredom.cinema_food.ui

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.boredom.cinema_food.data.CinemaFoodRepository
import com.boredom.cinema_food.ui.cart.CartViewModel
import com.boredom.cinema_food.ui.checkout.CheckoutViewModel
import com.boredom.cinema_food.ui.checkout.promo.PromoViewModel
import com.boredom.cinema_food.ui.history.HistoryViewModel
import com.boredom.cinema_food.ui.home.HomeViewModel
import com.boredom.cinema_food.ui.home.order.OrderViewModel
import com.boredom.cinema_food.ui.profile.ProfileViewModel

class ViewModelFactory private constructor(private val mCinemaFoodRepository: CinemaFoodRepository) :
    ViewModelProvider.NewInstanceFactory() {
    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(context: Context): ViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: ViewModelFactory(
                    CinemaFoodRepository.getInstance(context)
                )
            }
    }

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(HomeViewModel::class.java) -> {
                HomeViewModel(mCinemaFoodRepository) as T
            }
            modelClass.isAssignableFrom(CartViewModel::class.java) -> {
                CartViewModel(mCinemaFoodRepository) as T
            }
            modelClass.isAssignableFrom(OrderViewModel::class.java) -> {
                OrderViewModel(mCinemaFoodRepository) as T
            }
            modelClass.isAssignableFrom(CheckoutViewModel::class.java) -> {
                CheckoutViewModel(mCinemaFoodRepository) as T
            }
            modelClass.isAssignableFrom(PromoViewModel::class.java) -> {
                PromoViewModel(mCinemaFoodRepository) as T
            }
            modelClass.isAssignableFrom(HistoryViewModel::class.java) -> {
                HistoryViewModel(mCinemaFoodRepository) as T
            }
            modelClass.isAssignableFrom(ProfileViewModel::class.java) -> {
                ProfileViewModel(mCinemaFoodRepository) as T
            }
            else -> {
                throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
            }
        }
    }
}