package com.boredom.cinema_food.data

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.sqlite.db.SupportSQLiteQuery
import com.boredom.cinema_food.data.entity.CouponEntity
import com.boredom.cinema_food.data.entity.HistoryEntity
import com.boredom.cinema_food.data.entity.ItemOrderEntity
import com.boredom.cinema_food.data.entity.MovieEntity

@Dao
interface CinemaDao {
    @Query("SELECT * FROM item_order")
    fun getItemOrders(): LiveData<List<ItemOrderEntity>>

    @RawQuery(observedEntities = [MovieEntity::class])
    fun getAllMovies(query: SupportSQLiteQuery): LiveData<List<MovieEntity>>

    @Query("SELECT * FROM coupon")
    fun getAllCoupons(): LiveData<List<CouponEntity>>

    @Query("SELECT * FROM history")
    fun getAllHistories(): LiveData<List<HistoryEntity>>

    @Insert
    fun insertItemOrder(itemOrderEntity: ItemOrderEntity)

    @Insert
    fun insertHistory(historyEntity: HistoryEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovieAll(vararg movieEntity: MovieEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCouponAll(vararg couponEntity: CouponEntity)

    @Delete
    fun deleteCoupon(couponEntity: CouponEntity)

    @Delete
    fun deleteOrder(orderEntity: ItemOrderEntity)

    @Query("DELETE FROM item_order")
    fun deleteOrders()

    @Query("SELECT item_quantity FROM item_order WHERE id = :id LIMIT 1")
    fun getItemQuantityWithId(id: Int): LiveData<Int>

    @Query("UPDATE item_order SET item_quantity = item_quantity-1 WHERE id = :id")
    fun minusQuantityWithId(id: Int)

    @Query("UPDATE item_order SET item_quantity = item_quantity+1 WHERE id = :id")
    fun plusQuantityWithId(id: Int)

    @Query("UPDATE item_order SET item_quantity = 1 WHERE item_quantity = 0")
    fun constraintItemQuantity()
}