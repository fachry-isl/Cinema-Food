package com.boredom.cinema_food.utils

import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.*

object NumberFormatterUtils {
    fun format(number: Int): String {
        //Prepare number formatter
        val formatter = NumberFormat.getInstance(Locale.US) as DecimalFormat
        val symbols = formatter.decimalFormatSymbols

        symbols.groupingSeparator = '.'
        formatter.decimalFormatSymbols = symbols
        return formatter.format(number)
    }
}