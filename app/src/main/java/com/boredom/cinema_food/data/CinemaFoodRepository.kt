package com.boredom.cinema_food.data

import android.content.Context
import androidx.lifecycle.LiveData
import com.boredom.cinema_food.data.entity.CouponEntity
import com.boredom.cinema_food.data.entity.HistoryEntity
import com.boredom.cinema_food.data.entity.ItemOrderEntity
import com.boredom.cinema_food.data.entity.MovieEntity
import com.boredom.cinema_food.utils.DayUtils
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class CinemaFoodRepository(
    private val cinemaDao: CinemaDao,
    private val executor: ExecutorService
) {
    companion object {
        @Volatile
        private var instance: CinemaFoodRepository? = null

        fun getInstance(context: Context): CinemaFoodRepository {
            return instance ?: synchronized(this) {
                if (instance == null) {
                    val database = CinemaDatabase.getInstance(context)
                    instance = CinemaFoodRepository(
                        database.cinemaDao(),
                        Executors.newSingleThreadExecutor()
                    )
                }
                return instance as CinemaFoodRepository
            }

        }
    }

    fun getOrders(): LiveData<List<ItemOrderEntity>> = cinemaDao.getItemOrders()

    fun getMovies(sort: String): LiveData<List<MovieEntity>> {
        val query = DayUtils.getSortedQuery(sort)
        return cinemaDao.getAllMovies(query)
    }

    fun getCoupons(): LiveData<List<CouponEntity>> = cinemaDao.getAllCoupons()

    fun getHistories(): LiveData<List<HistoryEntity>> = cinemaDao.getAllHistories()

    fun getItemQuantityWithId(id: Int): LiveData<Int> = cinemaDao.getItemQuantityWithId(id)

    fun deleteCoupon(couponEntity: CouponEntity) {
        executor.execute { cinemaDao.deleteCoupon(couponEntity) }
    }

    fun insertItemOrder(order: ItemOrderEntity) {
        executor.execute { cinemaDao.insertItemOrder(order) }
    }

    fun insertHistory(history: HistoryEntity) {
        executor.execute { cinemaDao.insertHistory(history) }
    }

    fun deleteOrders() {
        executor.execute { cinemaDao.deleteOrders() }
    }

    fun deleteOrder(order: ItemOrderEntity) {
        executor.execute { cinemaDao.deleteOrder(order) }
    }

    fun minusQuantityItemOrder(id: Int) {
        executor.execute { cinemaDao.minusQuantityWithId(id) }
    }

    fun plusQuantityItemOrder(id: Int) {
        executor.execute { cinemaDao.plusQuantityWithId(id) }
    }

    fun constraintItemQuantity() {
        executor.execute { cinemaDao.constraintItemQuantity() }
    }
}