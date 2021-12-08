package com.boredom.cinema_food.utils

import com.boredom.cinema_food.R
import com.boredom.cinema_food.data.entity.FoodEntity
import com.boredom.cinema_food.data.entity.MovieEntity

object DataDummy {
    fun generateDummyMovies(): List<MovieEntity> {
        val movies = ArrayList<MovieEntity>()

        movies.add(
            MovieEntity(
                1,
                "Rons Gone Wrong",
                R.drawable.poster_rons,
                "In a world where walking, talking, digitally connected bots have become children's best friends, an 11-year-old finds that his robot buddy doesn't quite work the same as the others do.",
                30,
                "11:45am",
                "Sunday"
            )
        )

        movies.add(
            MovieEntity(
                2,
                "The Last Duel",
                R.drawable.poster_the_last_duel,
                "King Charles VI declares that Knight Jean de Carrouges settle his dispute with his squire, Jacques Le Gris, by challenging him to a duel.",
                30,
                "01:30pm",
                "Sunday"
            )
        )

        movies.add(
            MovieEntity(
                3,
                "Earwig and the Witch",
                R.drawable.poster_earwig,
                "A headstrong orphan discovers a world of spells and potions while living with a selfish witch.",
                30,
                "03:30pm",
                "Sunday"
            )
        )

        movies.add(
            MovieEntity(
                4,
                "Dune",
                R.drawable.poster_dune,
                "Paul Atreides, a brilliant and gifted young man born into a great destiny beyond his understanding, must travel to the most dangerous planet in the universe to ensure the future of his family and his people. As malevolent forces explode into conflict over the planet's exclusive supply of the most precious resource in existence-a commodity capable of unlocking humanity's greatest potential-only those who can conquer their fear will survive.",
                30,
                "04:00pm",
                "Sunday"
            )
        )

        movies.add(
            MovieEntity(
                5,
                "Aquaman",
                R.drawable.poster_aquaman,
                "Once home to the most advanced civilization on Earth, Atlantis is now an underwater kingdom ruled by the power-hungry King Orm. With a vast army at his disposal, Orm plans to conquer the remaining oceanic people and then the surface world. Standing in his way is Arthur Curry, Orm's half-human, half-Atlantean brother and true heir to the throne.",
                30,
                "10:00am",
                "Monday"
            )
        )

        movies.add(
            MovieEntity(
                6,
                "Fantastic Beasts: The Crimes of Grindelwald",
                R.drawable.poster_crimes,
                "Gellert Grindelwald has escaped imprisonment and has begun gathering followers to his cause—elevating wizards above all non-magical beings. The only one capable of putting a stop to him is the wizard he once called his closest friend, Albus Dumbledore. However, Dumbledore will need to seek help from the wizard who had thwarted Grindelwald once before, his former student Newt Scamander, who agrees to help, unaware of the dangers that lie ahead. Lines are drawn as love and loyalty are tested, even among the truest friends and family, in an increasingly divided wizarding world.",
                30,
                "12:00am",
                "Monday"
            )
        )


        movies.add(
            MovieEntity(
                7,
                "Earwig and the Witch",
                R.drawable.poster_earwig,
                "A headstrong orphan discovers a world of spells and potions while living with a selfish witch.",
                30,
                "03:30pm",
                "Monday"
            )
        )

        movies.add(
            MovieEntity(
                8,
                "Dune",
                R.drawable.poster_dune,
                "Paul Atreides, a brilliant and gifted young man born into a great destiny beyond his understanding, must travel to the most dangerous planet in the universe to ensure the future of his family and his people. As malevolent forces explode into conflict over the planet's exclusive supply of the most precious resource in existence-a commodity capable of unlocking humanity's greatest potential-only those who can conquer their fear will survive.",
                30,
                "04:00pm",
                "Monday"
            )
        )

        movies.add(
            MovieEntity(
                9,
                "How to Train Your Dragon",
                R.drawable.poster_how_to_train,
                "As the son of a Viking leader on the cusp of manhood, shy Hiccup Horrendous Haddock III faces a rite of passage: he must kill a dragon to prove his warrior mettle. But after downing a feared dragon, he realizes that he no longer wants to destroy it, and instead befriends the beast – which he names Toothless – much to the chagrin of his warrior father",
                30,
                "10:00am",
                "Tuesday"
            )
        )

        movies.add(
            MovieEntity(
                10,
                "Avengers: Infinity War",
                R.drawable.poster_infinity_war,
                "As the Avengers and their allies have continued to protect the world from threats too large for any one hero to handle, a new danger has emerged from the cosmic shadows: Thanos. A despot of intergalactic infamy, his goal is to collect all six Infinity Stones, artifacts of unimaginable power, and use them to inflict his twisted will on all of reality. Everything the Avengers have fought for has led up to this moment - the fate of Earth and existence itself has never been more uncertain.",
                30,
                "01:00pm",
                "Tuesday"
            )
        )

        movies.add(
            MovieEntity(
                11,
                "Aquaman",
                R.drawable.poster_aquaman,
                "Once home to the most advanced civilization on Earth, Atlantis is now an underwater kingdom ruled by the power-hungry King Orm. With a vast army at his disposal, Orm plans to conquer the remaining oceanic people and then the surface world. Standing in his way is Arthur Curry, Orm's half-human, half-Atlantean brother and true heir to the throne.",
                30,
                "10:00am",
                "Tuesday"
            )
        )

        movies.add(
            MovieEntity(
                12,
                "Fantastic Beasts: The Crimes of Grindelwald",
                R.drawable.poster_crimes,
                "Gellert Grindelwald has escaped imprisonment and has begun gathering followers to his cause—elevating wizards above all non-magical beings. The only one capable of putting a stop to him is the wizard he once called his closest friend, Albus Dumbledore. However, Dumbledore will need to seek help from the wizard who had thwarted Grindelwald once before, his former student Newt Scamander, who agrees to help, unaware of the dangers that lie ahead. Lines are drawn as love and loyalty are tested, even among the truest friends and family, in an increasingly divided wizarding world.",
                30,
                "12:00am",
                "Tuesday"
            )
        )


        movies.add(
            MovieEntity(
                13,
                "Mortal Engines",
                R.drawable.poster_mortal_engines,
                "Many thousands of years in the future, Earth’s cities roam the globe on huge wheels, devouring each other in a struggle for ever diminishing resources. On one of these massive traction cities, the old London, Tom Natsworthy has an unexpected encounter with a mysterious young woman from the wastelands who will change the course of his life forever.",
                30,
                "10:00am",
                "Wednesday"
            )
        )

        movies.add(
            MovieEntity(
                14,
                "Ralph Breaks the Internet",
                R.drawable.poster_ralph,
                "Video game bad guy Ralph and fellow misfit Vanellope von Schweetz must risk it all by traveling to the World Wide Web in search of a replacement part to save Vanellope's video game, Sugar Rush. In way over their heads, Ralph and Vanellope rely on the citizens of the internet — the netizens — to help navigate their way, including an entrepreneur named Yesss, who is the head algorithm and the heart and soul of trend-making site BuzzzTube.",
                30,
                "01:00pm",
                "Wednesday"
            )
        )

        movies.add(
            MovieEntity(
                15,
                "Rons Gone Wrong",
                R.drawable.poster_rons,
                "In a world where walking, talking, digitally connected bots have become children's best friends, an 11-year-old finds that his robot buddy doesn't quite work the same as the others do.",
                30,
                "11:45am",
                "Wednesday"
            )
        )

        movies.add(
            MovieEntity(
                16,
                "The Last Duel",
                R.drawable.poster_the_last_duel,
                "King Charles VI declares that Knight Jean de Carrouges settle his dispute with his squire, Jacques Le Gris, by challenging him to a duel.",
                30,
                "01:30pm",
                "Wednesday"
            )
        )

        movies.add(
            MovieEntity(
                17,
                "Robin Hood",
                R.drawable.poster_robin_hood,
                "A war-hardened Crusader and his Moorish commander mount an audacious revolt against the corrupt English crown.",
                30,
                "10:00am",
                "Thursday"
            )
        )

        movies.add(
            MovieEntity(
                18,
                "Spider-Man: Far From Home",
                R.drawable.poster_spiderman,
                "Peter Parker and his friends go on a summer trip to Europe. However, they will hardly be able to rest - Peter will have to agree to help Nick Fury uncover the mystery of creatures that cause natural disasters and destruction throughout the continent.",
                30,
                "12:00am",
                "Thursday"
            )
        )

        movies.add(
            MovieEntity(
                19,
                "Avengers: Infinity War",
                R.drawable.poster_infinity_war,
                "As the Avengers and their allies have continued to protect the world from threats too large for any one hero to handle, a new danger has emerged from the cosmic shadows: Thanos. A despot of intergalactic infamy, his goal is to collect all six Infinity Stones, artifacts of unimaginable power, and use them to inflict his twisted will on all of reality. Everything the Avengers have fought for has led up to this moment - the fate of Earth and existence itself has never been more uncertain.",
                30,
                "01:00pm",
                "Thursday"
            )
        )

        movies.add(
            MovieEntity(
                20,
                "Aquaman",
                R.drawable.poster_aquaman,
                "Once home to the most advanced civilization on Earth, Atlantis is now an underwater kingdom ruled by the power-hungry King Orm. With a vast army at his disposal, Orm plans to conquer the remaining oceanic people and then the surface world. Standing in his way is Arthur Curry, Orm's half-human, half-Atlantean brother and true heir to the throne.",
                30,
                "08:00pm",
                "Thursday"
            )
        )

        movies.add(
            MovieEntity(
                21,
                "T - 34",
                R.drawable.poster_t34,
                "In 1944, a courageous group of Russian soldiers managed to escape from German captivity in a half-destroyed legendary T-34 tank. Those were the times of unforgettable bravery, fierce fighting, unbreakable love, and legendary miracles.",
                30,
                "10:00am",
                "Friday"
            )
        )

        movies.add(
            MovieEntity(
                22,
                "Dune",
                R.drawable.poster_dune,
                "Paul Atreides, a brilliant and gifted young man born into a great destiny beyond his understanding, must travel to the most dangerous planet in the universe to ensure the future of his family and his people. As malevolent forces explode into conflict over the planet's exclusive supply of the most precious resource in existence-a commodity capable of unlocking humanity's greatest potential-only those who can conquer their fear will survive.",
                30,
                "04:00pm",
                "Friday"
            )
        )

        movies.add(
            MovieEntity(
                23,
                "Robin Hood",
                R.drawable.poster_robin_hood,
                "A war-hardened Crusader and his Moorish commander mount an audacious revolt against the corrupt English crown.",
                30,
                "10:00am",
                "Friday"
            )
        )

        movies.add(
            MovieEntity(
                24,
                "Spider-Man: Far From Home",
                R.drawable.poster_spiderman,
                "Peter Parker and his friends go on a summer trip to Europe. However, they will hardly be able to rest - Peter will have to agree to help Nick Fury uncover the mystery of creatures that cause natural disasters and destruction throughout the continent.",
                30,
                "12:00am",
                "Friday"
            )
        )

        movies.add(
            MovieEntity(
                25,
                "Fantastic Beasts: The Crimes of Grindelwald",
                R.drawable.poster_crimes,
                "Gellert Grindelwald has escaped imprisonment and has begun gathering followers to his cause—elevating wizards above all non-magical beings. The only one capable of putting a stop to him is the wizard he once called his closest friend, Albus Dumbledore. However, Dumbledore will need to seek help from the wizard who had thwarted Grindelwald once before, his former student Newt Scamander, who agrees to help, unaware of the dangers that lie ahead. Lines are drawn as love and loyalty are tested, even among the truest friends and family, in an increasingly divided wizarding world.",
                30,
                "12:00am",
                "Saturday"
            )
        )


        movies.add(
            MovieEntity(
                26,
                "Earwig and the Witch",
                R.drawable.poster_earwig,
                "A headstrong orphan discovers a world of spells and potions while living with a selfish witch.",
                30,
                "03:30pm",
                "Saturday"
            )
        )

        movies.add(
            MovieEntity(
                27,
                "Rons Gone Wrong",
                R.drawable.poster_rons,
                "In a world where walking, talking, digitally connected bots have become children's best friends, an 11-year-old finds that his robot buddy doesn't quite work the same as the others do.",
                30,
                "11:45am",
                "Saturday"
            )
        )

        movies.add(
            MovieEntity(
                28,
                "The Last Duel",
                R.drawable.poster_the_last_duel,
                "King Charles VI declares that Knight Jean de Carrouges settle his dispute with his squire, Jacques Le Gris, by challenging him to a duel.",
                30,
                "01:30pm",
                "Saturday"
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