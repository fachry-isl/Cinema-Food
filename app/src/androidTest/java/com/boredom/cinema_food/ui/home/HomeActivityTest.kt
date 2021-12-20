package com.boredom.cinema_food.ui.home

import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.scrollTo
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.boredom.cinema_food.R
import com.boredom.cinema_food.utils.DataDummy
import com.boredom.cinema_food.utils.EspressoIdlingResource
import org.junit.After
import org.junit.Before
import org.junit.Test

class HomeActivityTest {
    private val dummyMovie = DataDummy.generateDummyMovies()

    @Before
    fun setUp() {
        ActivityScenario.launch(HomeActivity::class.java)
        IdlingRegistry.getInstance().register(EspressoIdlingResource.idlingResource)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.idlingResource)
    }

    @Test
    fun loadMovies() {
        ActivityScenario.launch(HomeActivity::class.java)
        Espresso.onView(withId(R.id.rv_movie))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.rv_movie)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dummyMovie.size))
    }

    @Test
    fun loadDetailMovie() {
        Espresso.onView(withId(R.id.rv_movie)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0,
            click()
        ))
        Espresso.onView(withId(R.id.tv_title_movie_order))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.tv_title_movie_order))
            .check(ViewAssertions.matches(ViewMatchers.withText(dummyMovie[0].title)))
        Espresso.onView(withId(R.id.iv_movie_order))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.tv_desc_movie_order))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.tv_desc_movie_order))
            .check(ViewAssertions.matches(ViewMatchers.withText(dummyMovie[0].description)))
    }

    @Test
    fun loadBeverages() {
        Espresso.onView(withId(R.id.rv_movie)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0,
                click()
            ))
        Espresso.onView(withId(R.id.textView5))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.textView5))
            .check(ViewAssertions.matches(ViewMatchers.withText("Mineral Water")))
        Espresso.onView(withId(R.id.radio0)).perform(click())
        Espresso.onView(withId(R.id.radio0))
            .check(ViewAssertions.matches(ViewMatchers.withText("Normal")))
        Espresso.onView(withId(R.id.textView7))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.textView7))
            .check(ViewAssertions.matches(ViewMatchers.withText("Tea")))
        Espresso.onView(withId(R.id.btn_ice)).perform(click())
        Espresso.onView(withId(R.id.btn_ice))
            .check(ViewAssertions.matches(ViewMatchers.withText("Ice")))
        Espresso.onView(withId(R.id.btn_resetOrder)).perform(click())
    }

    @Test
    fun loadFood(){
        Espresso.onView(withId(R.id.rv_movie)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0,
                click()
            ))
        Espresso.onView(withId(R.id.rv_food))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.btn_ice)).perform(click())
        Espresso.onView(withId(R.id.btn_addOrder)).perform(scrollTo(), click())
    }

    @Test
    fun checkout(){
        Espresso.onView(withId(R.id.navigation_cart)).perform(click())
        Espresso.onView(withId(R.id.btn_goCheckout)).perform(click())
        Espresso.onView(withId(R.id.button1)).perform(click())
        Espresso.onView(withId(R.id.button3)).perform(click())
        Espresso.onView(withId(R.id.btn_order)).perform(click())
        Espresso.onView(withId(R.id.navigation_history)).perform(click())
        Espresso.onView(withId(R.id.tv_date_history))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }
}
