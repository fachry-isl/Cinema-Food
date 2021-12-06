package com.boredom.cinema_food.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.boredom.cinema_food.data.entity.ItemOrderEntity

@Database(entities = [ItemOrderEntity::class], version = 1, exportSchema = false)
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
                ).build().apply {
                    INSTANCE = this
                }
            }
    }
}