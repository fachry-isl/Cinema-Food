package com.boredom.cinema_food.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.boredom.cinema_food.data.entity.CouponEntity
import com.boredom.cinema_food.data.entity.ItemOrderEntity
import com.boredom.cinema_food.data.entity.MovieEntity
import com.boredom.cinema_food.utils.DataDummy
import org.json.JSONException
import java.util.concurrent.Executors

@Database(
    entities = [ItemOrderEntity::class, MovieEntity::class, CouponEntity::class],
    version = 1,
    exportSchema = false
)
abstract class CinemaDatabase : RoomDatabase() {
    abstract fun cinemaDao(): CinemaDao

    companion object {

        @Volatile
        private var INSTANCE: CinemaDatabase? = null

        fun getInstance(context: Context): CinemaDatabase =
            INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    CinemaDatabase::class.java,
                    "Cinema.db"
                ).addCallback(object : Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        fillWithStartingData()
                    }
                })
                    .build().apply {
                        INSTANCE = this
                    }
            }

        private fun fillWithStartingData() {
            Executors.newSingleThreadExecutor().execute {
                val movie = DataDummy.generateDummyMovies()
                val coupon = DataDummy.generateDummyCoupon()
                try {
                    for (item in movie) {
                        INSTANCE?.cinemaDao()?.insertMovieAll(
                            MovieEntity(
                                item.movieId,
                                item.title,
                                item.image,
                                item.description,
                                item.ticketRemaining,
                                item.time,
                                item.day
                            )
                        )
                    }
                    for (item in coupon) {
                        INSTANCE?.cinemaDao()?.insertCouponAll(
                            CouponEntity(
                                item.id,
                                item.title,
                                item.description,
                                item.discount,
                                item.time_last
                            )
                        )
                    }
                } catch (exception: JSONException) {
                    exception.printStackTrace()
                }
            }
        }
    }
}