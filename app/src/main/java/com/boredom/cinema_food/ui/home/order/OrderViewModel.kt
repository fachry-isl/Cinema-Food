package com.boredom.cinema_food.ui.home.order

import androidx.lifecycle.ViewModel
import com.boredom.cinema_food.data.CinemaFoodRepository
import com.boredom.cinema_food.data.entity.ItemOrderEntity

class OrderViewModel(private val cinemaFoodRepository: CinemaFoodRepository) : ViewModel() {
    fun insertItemOrder(item: ItemOrderEntity) {
        cinemaFoodRepository.insertItemOrder(item)
    }
}