package com.boredom.cinema_food.ui.checkout.promo

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.boredom.cinema_food.data.CinemaFoodRepository
import com.boredom.cinema_food.data.entity.CouponEntity

class PromoViewModel(mCinemaFoodRepository: CinemaFoodRepository) : ViewModel() {
    val coupons: LiveData<List<CouponEntity>> = mCinemaFoodRepository.getCoupons()
}