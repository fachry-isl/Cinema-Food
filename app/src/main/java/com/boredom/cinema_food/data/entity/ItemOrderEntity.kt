package com.boredom.cinema_food.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "item_order")
data class ItemOrderEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var itemId: Int = 0,

    @ColumnInfo(name = "item_name")
    var itemName: String?,

    @ColumnInfo(name = "item_image")
    var itemImage: Int?,

    @ColumnInfo(name = "item_description")
    var itemDesc: String?,

    @ColumnInfo(name = "item_price")
    var itemPrice: Int?,

    @ColumnInfo(name = "item_quantity")
    var itemQuantity: Int? = null
)


