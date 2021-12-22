package com.boredom.cinema_food.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.boredom.cinema_food.data.CinemaFoodRepository

class ProfileViewModel(private val mCinemaFoodRepository: CinemaFoodRepository) : ViewModel() {
    fun getItemHistoriesCount(): LiveData<Int> = mCinemaFoodRepository.getItemHistoriesCount()
    fun getItemCouponsCount(): LiveData<Int> = mCinemaFoodRepository.getItemCouponsCount()
}