package com.boredom.cinema_food.data.entity


import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize


@Parcelize
@Entity(tableName = "movie")
data class MovieEntity(
    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true)
    var movieId: Int,
    @ColumnInfo(name = "title")
    var title: String,
    @ColumnInfo(name = "image")
    var image: Int,
    @ColumnInfo(name = "description")
    var description: String,
    @ColumnInfo(name = "ticket_remaining")
    var ticketRemaining: Int,
    @ColumnInfo(name = "time")
    var time: String,
    @ColumnInfo(name = "day")
    var day: String
) : Parcelable

