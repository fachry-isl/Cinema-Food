package com.boredom.cinema_food.ui.checkout

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.boredom.cinema_food.data.CinemaFoodRepository
import com.boredom.cinema_food.data.entity.CouponEntity
import com.boredom.cinema_food.data.entity.HistoryEntity
import com.boredom.cinema_food.data.entity.ItemOrderEntity
import kotlinx.coroutines.launch

class CheckoutViewModel(private val mCinemaFoodRepository: CinemaFoodRepository) : ViewModel() {
    val orders: LiveData<List<ItemOrderEntity>> = mCinemaFoodRepository.getOrders()

    fun getItemQuantityWithId(id: Int): LiveData<Int> =
        mCinemaFoodRepository.getItemQuantityWithId(id)

    fun deleteCoupon(couponEntity: CouponEntity) {
        viewModelScope.launch {
            mCinemaFoodRepository.deleteCoupon(couponEntity)
        }
    }

    fun insertHistory(historyEntity: HistoryEntity) {
        viewModelScope.launch {
            mCinemaFoodRepository.insertHistory(historyEntity)
        }
    }

    fun deleteOrders() {
        viewModelScope.launch {
            mCinemaFoodRepository.deleteOrders()
        }
    }

    fun deleteOrder(order: ItemOrderEntity) {
        viewModelScope.launch {
            mCinemaFoodRepository.deleteOrder(order)
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