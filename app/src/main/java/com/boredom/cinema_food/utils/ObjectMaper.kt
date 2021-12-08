package com.boredom.cinema_food.utils

import com.boredom.cinema_food.R

object ObjectMaper {
    fun getMoviePrice(itemName: String?): Int? {
        val prices = HashMap<String, Int>()
        prices["Rons Gone Wrong"] = 40_000
        prices["The Last Duel"] = 30_000
        prices["Earwig and the Witch"] = 30_000
        prices["Dune"] = 40_000
        prices["Aquaman"] = 30_000
        prices["Fantastic Beasts: The Crimes of Grindelwald"] = 40_000
        prices["How to Train Your Dragon"] = 30_000
        prices["Avengers: Infinity War"] = 40_000
        prices["Mortal Engines"] = 35_000
        prices["Ralph Breaks the Internet"] = 35_000
        prices["The Last Duel"] = 35_000
        prices["Robin Hood"] = 35_000
        prices["Spider-Man: Far From Home"] = 40_000
        prices["T - 34"] = 35_000

        return prices[itemName]
    }

    fun getMovieImage(itemName: String?): Int? {
        val images = HashMap<String, Int>()
        images["Rons Gone Wrong"] = R.drawable.poster_rons
        images["The Last Duel"] = R.drawable.poster_the_last_duel
        images["Earwig and the Witch"] = R.drawable.poster_earwig
        images["Dune"] = R.drawable.poster_dune
        images["Aquaman"] = R.drawable.poster_aquaman
        images["Fantastic Beasts: The Crimes of Grindelwald"] = R.drawable.poster_crimes
        images["How to Train Your Dragon"] = R.drawable.poster_how_to_train
        images["Avengers: Infinity War"] = R.drawable.poster_infinity_war
        images["Mortal Engines"] = R.drawable.poster_mortal_engines
        images["Ralph Breaks the Internet"] = R.drawable.poster_ralph
        images["The Last Duel"] = R.drawable.poster_the_last_duel
        images["Robin Hood"] = R.drawable.poster_robin_hood
        images["Spider-Man: Far From Home"] = R.drawable.poster_spiderman
        images["T - 34"] = R.drawable.poster_t34

        return images[itemName]
    }

    fun getWaterPrice(itemName: String?): Int? {
        val prices = HashMap<String, Int>()
        prices["Normal Mineral Water"] = 2500
        prices["Cold Mineral Water"] = 2500

        return prices[itemName]
    }

    fun getWaterImage(itemName: String?): Int? {
        val images = HashMap<String, Int>()
        images["Normal Mineral Water"] = R.drawable.mineral_water
        images["Cold Mineral Water"] = R.drawable.mineral_water

        return images[itemName]
    }

    fun getCoffeePrice(itemName: String?): Int? {
        val prices = HashMap<String, Int>()
        prices["Arabica Coffee"] = 12_000
        prices["Robusta Coffee"] = 14_000
        prices["Gayo Coffee"] = 12_000

        return prices[itemName]
    }

    fun getCoffeeImage(itemName: String?): Int? {
        val images = HashMap<String, Int>()

        images["Arabica Coffee"] = R.drawable.coffee_arabica
        images["Robusta Coffee"] = R.drawable.coffee_robusta
        images["Gayo Coffee"] = R.drawable.coffee_gayo
        return images[itemName]
    }

    fun getTeaPrice(itemName: String?): Int? {
        val prices = HashMap<String, Int>()
        prices["Hot Tea"] = 3000
        prices["Ice Tea"] = 3000

        return prices[itemName]
    }

    fun getTeaImage(itemName: String?): Int? {
        val images = HashMap<String, Int>()
        images["Hot Tea"] = R.drawable.hot_tea
        images["Ice Tea"] = R.drawable.ice_tea

        return images[itemName]
    }

    fun getJuicePrice(itemName: String?): Int? {
        val prices = HashMap<String, Int>()
        prices["Avocado Juice"] = 14_000
        prices["Guava Juice"] = 12_000
        prices["Orange Juice"] = 10_000

        return prices[itemName]
    }

    fun getJuiceImage(itemName: String?): Int? {
        val images = HashMap<String, Int>()
        images["Avocado Juice"] = R.drawable.avocado_juice
        images["Guava Juice"] = R.drawable.guava_juice
        images["Orange Juice"] = R.drawable.orange_juice

        return images[itemName]
    }

    fun getFoodDesc(itemName: String?): String? {
        val images = HashMap<String, String>()
        images["Fried Rice"] = "Special fried rice with egg"
        images["French Fries"] = "Potato french fries"
        images["Fried Bananas"] = "4 fried banana with Chocolate"
        images["Nuggets"] = "5 nuggets with sauce"
        images["Taichan"] = "12 pcs sate taichan with peanut sauce"
        images["Toast"] = "1 Special Toast"

        return images[itemName]
    }

    fun getFoodPrice(itemName: String?): Int? {
        val prices = HashMap<String, Int>()
        prices["Fried Rice"] = 15_000
        prices["French Fries"] = 12_000
        prices["Fried Bananas"] = 10_000
        prices["Nuggets"] = 12_000
        prices["Taichan"] = 27_000
        prices["Toast"] = 10_000

        return prices[itemName]
    }

    fun getFoodImage(itemName: String?): Int? {
        val images = HashMap<String, Int>()
        images["Fried Rice"] = R.drawable.fried_rice
        images["French Fries"] = R.drawable.french_fries
        images["Fried Bananas"] = R.drawable.fried_banana
        images["Nuggets"] = R.drawable.nuggets
        images["Taichan"] = R.drawable.taichan
        images["Toast"] = R.drawable.toast

        return images[itemName]
    }
}