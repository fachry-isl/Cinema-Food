package com.boredom.cinema_food.utils

import com.boredom.cinema_food.R
import com.boredom.cinema_food.data.entity.FoodEntity
import com.boredom.cinema_food.data.entity.MovieEntity

object DataDummy {
    fun generateDummyMovies(): List<MovieEntity> {
        val movies = ArrayList<MovieEntity>()

        movies.add(
            MovieEntity(
                "m01",
                "Rons Gone Wrong",
                R.drawable.poster_rons,
                "In a world where walking, talking, digitally connected bots have become children's best friends, an 11-year-old finds that his robot buddy doesn't quite work the same as the others do.",
                11,
                "11:45am"
            )
        )

        movies.add(
            MovieEntity(
                "m02",
                "The Last Duel",
                R.drawable.poster_the_last_duel,
                "King Charles VI declares that Knight Jean de Carrouges settle his dispute with his squire, Jacques Le Gris, by challenging him to a duel.",
                6,
                "01:30pm",
            )
        )

        movies.add(
            MovieEntity(
                "m03",
                "Earwig and the Witch",
                R.drawable.poster_earwig,
                "A headstrong orphan discovers a world of spells and potions while living with a selfish witch.",
                17,
                "03:30pm"
            )
        )

        movies.add(
            MovieEntity(
                "m04",
                "Dune",
                R.drawable.poster_dune,
                "Paul Atreides, a brilliant and gifted young man born into a great destiny beyond his understanding, must travel to the most dangerous planet in the universe to ensure the future of his family and his people. As malevolent forces explode into conflict over the planet's exclusive supply of the most precious resource in existence-a commodity capable of unlocking humanity's greatest potential-only those who can conquer their fear will survive.",
                1,
                "04:00pm"
            )
        )

        return movies
    }

    fun generateDummyFood(): List<FoodEntity> {
        val list = ArrayList<FoodEntity>()
        list.add(
            FoodEntity(
                "f01",
                "Fried Rice",
                R.drawable.fried_rice,
            )
        )

        list.add(
            FoodEntity(
                "f02",
                "French Fries",
                R.drawable.french_fries,
            )
        )

        list.add(
            FoodEntity(
                "f03",
                "Fried Bananas",
                R.drawable.fried_banana,
            )
        )

        list.add(
            FoodEntity(
                "f04",
                "Nuggets",
                R.drawable.nuggets,
            )
        )

        list.add(
            FoodEntity(
                "f05",
                "Taichan",
                R.drawable.taichan,
            )
        )

        list.add(
            FoodEntity(
                "f06",
                "Toast",
                R.drawable.toast,
            )
        )


        return list
    }
}