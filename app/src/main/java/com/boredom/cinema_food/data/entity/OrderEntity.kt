package com.boredom.cinema_food.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "order")
data class OrderEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var orderId: Int = 0,

    @ColumnInfo(name = "movie")
    var movie: String? = null,

    @ColumnInfo(name = "mineral_water")
    var mineralWater: String? = null,

    @ColumnInfo(name = "coffee")
    var coffee: String? = null,

    @ColumnInfo(name = "tea")
    var tea: String? = null,

    @ColumnInfo(name = "juice")
    var juice: String? = null,

    @ColumnInfo(name = "food")
    var food: String? = null
)

