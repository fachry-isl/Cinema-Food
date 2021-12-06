package com.boredom.cinema_food.data.entity


import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class MovieEntity(
    var movieId: String,
    var title: String,
    var image: Int,
    var description: String,
    var ticketRemaining: Int,
    var time: String
) : Parcelable

