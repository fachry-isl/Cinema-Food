package com.boredom.cinema_food.data

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.sqlite.db.SupportSQLiteQuery
import com.boredom.cinema_food.data.entity.CouponEntity
import com.boredom.cinema_food.data.entity.ItemOrderEntity
import com.boredom.cinema_food.data.entity.MovieEntity

@Dao
interface CinemaDao {
    @Query("SELECT * FROM item_order")
    fun getItemOrders(): LiveData<List<ItemOrderEntity>>

    @Insert
    fun insertItemOrder(itemOrderEntity: ItemOrderEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovieAll(vararg movieEntity: MovieEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCouponAll(vararg couponEntity: CouponEntity)

    @RawQuery(observedEntities = [MovieEntity::class])
    fun getAllMovies(query: SupportSQLiteQuery): LiveData<List<MovieEntity>>

    @Query("SELECT * FROM coupon")
    fun getAllCoupons(): LiveData<List<CouponEntity>>

    @Delete
    fun deleteCoupon(couponEntity: CouponEntity)

    @Delete
    fun deleteItemOrder(itemOrderEntity: ItemOrderEntity)

    @Query("UPDATE item_order SET item_quantity = item_quantity-1 WHERE id = :id")
    fun minusQuantityWithId(id: Int)

    @Query("UPDATE item_order SET item_quantity = item_quantity+1 WHERE id = :id")
    fun plusQuantityWithId(id: Int)

    @Query("UPDATE item_order SET item_quantity = 1 WHERE item_quantity = 0")
    fun constraintItemQuantity()
}