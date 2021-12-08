package com.boredom.cinema_food.ui

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.boredom.cinema_food.data.CinemaFoodRepository
import com.boredom.cinema_food.ui.cart.CartViewModel
import com.boredom.cinema_food.ui.home.HomeViewModel
import com.boredom.cinema_food.ui.home.order.OrderViewModel

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
            else -> {
                throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
            }
        }
    }
}