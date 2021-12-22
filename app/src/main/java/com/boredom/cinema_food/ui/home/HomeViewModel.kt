package com.boredom.cinema_food.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.boredom.cinema_food.data.CinemaFoodRepository
import com.boredom.cinema_food.data.entity.MovieEntity

class HomeViewModel(private val mCinemaFoodRepository: CinemaFoodRepository) : ViewModel() {
    fun getMovies(sort: String): LiveData<List<MovieEntity>> = mCinemaFoodRepository.getMovies(sort)
    fun getItemCouponsCount(): LiveData<Int> = mCinemaFoodRepository.getItemCouponsCount()
}