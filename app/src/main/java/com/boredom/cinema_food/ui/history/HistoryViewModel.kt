package com.boredom.cinema_food.ui.history

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.boredom.cinema_food.data.CinemaFoodRepository
import com.boredom.cinema_food.data.entity.HistoryEntity

class HistoryViewModel(mCinemaFoodRepository: CinemaFoodRepository) : ViewModel() {
    val histories: LiveData<List<HistoryEntity>> = mCinemaFoodRepository.getHistories()
}