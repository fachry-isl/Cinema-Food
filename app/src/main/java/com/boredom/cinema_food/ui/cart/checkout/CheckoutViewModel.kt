package com.boredom.cinema_food.ui.cart.checkout

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.boredom.cinema_food.data.CinemaFoodRepository
import com.boredom.cinema_food.data.entity.ItemOrderEntity
import kotlinx.coroutines.launch

class CheckoutViewModel(private val mCinemaFoodRepository: CinemaFoodRepository) : ViewModel() {
    val orders: LiveData<List<ItemOrderEntity>> = mCinemaFoodRepository.getOrders()
    fun deleteOrder(order: ItemOrderEntity) {
        viewModelScope.launch {
            mCinemaFoodRepository.deleteItemOrder(order)
        }
    }

    fun minusQuantityById(id: Int) {
        viewModelScope.launch {
            mCinemaFoodRepository.minusQuantityItemOrder(id)
        }
    }

    fun plusQuantityById(id: Int) {
        viewModelScope.launch {
            mCinemaFoodRepository.plusQuantityItemOrder(id)
        }
    }

    fun constraintItemQuantity() {
        viewModelScope.launch {
            mCinemaFoodRepository.constraintItemQuantity()
        }
    }
}