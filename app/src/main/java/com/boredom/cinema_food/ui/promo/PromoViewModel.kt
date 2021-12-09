package com.boredom.cinema_food.ui.promo

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.boredom.cinema_food.data.CinemaFoodRepository
import com.boredom.cinema_food.data.entity.CouponEntity
import kotlinx.coroutines.launch

class PromoViewModel(private val mCinemaFoodRepository: CinemaFoodRepository) : ViewModel() {
    val coupons: LiveData<List<CouponEntity>> = mCinemaFoodRepository.getCoupons()

    fun deleteCoupon(couponEntity: CouponEntity) {
        viewModelScope.launch {
            mCinemaFoodRepository.deleteCoupon(couponEntity)
        }
    }
}