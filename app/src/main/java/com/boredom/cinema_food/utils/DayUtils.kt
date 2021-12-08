package com.boredom.cinema_food.utils

import androidx.sqlite.db.SimpleSQLiteQuery

object DayUtils {
    const val SUNDAY = "Sunday"
    const val MONDAY = "Monday"
    const val TUESDAY = "Tuesday"
    const val WEDNESDAY = "Wednesday"
    const val THURSDAY = "Thursday"
    const val FRIDAY = "Friday"
    const val SATURDAY = "Saturday"

    fun getSortedQuery(filter: String): SimpleSQLiteQuery {
        val query = StringBuilder().append("SELECT * FROM movie ")
        when (filter) {
            SUNDAY -> query.append("WHERE day = 'Sunday'")
            MONDAY -> query.append("WHERE day = 'Monday'")
            TUESDAY -> query.append("WHERE day = 'Tuesday'")
            WEDNESDAY -> query.append("WHERE day = 'Wednesday'")
            THURSDAY -> query.append("WHERE day = 'Thursday'")
            FRIDAY -> query.append("WHERE day = 'Friday'")
            SATURDAY -> query.append("WHERE day = 'Saturday'")
        }
        return SimpleSQLiteQuery(query.toString())
    }
}