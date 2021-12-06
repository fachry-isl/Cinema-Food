package com.boredom.cinema_food.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.boredom.cinema_food.data.entity.ItemOrderEntity

@Dao
interface CinemaDao {
    @Query("SELECT * FROM item_order")
    fun getItemOrders(): LiveData<List<ItemOrderEntity>>

    @Insert
    fun insertItemOrder(itemOrderEntity: ItemOrderEntity)

    @Delete
    fun deleteItemOrder(itemOrderEntity: ItemOrderEntity)

    @Query("UPDATE item_order SET item_quantity = item_quantity-1 WHERE id = :id")
    fun minusQuantityWithId(id: Int)

    @Query("UPDATE item_order SET item_quantity = item_quantity+1 WHERE id = :id")
    fun plusQuantityWithId(id: Int)

    @Query("UPDATE item_order SET item_quantity = 1 WHERE item_quantity = 0")
    fun constraintQuantity()
}