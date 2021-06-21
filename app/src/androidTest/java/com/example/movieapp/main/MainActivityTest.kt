package com.example.movieapp.main

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.example.movieapp.R
import com.example.movieapp.ui.activity.MainActivity
import com.example.movieapp.utils.EspressoIdlingResource
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class MainActivityInstrumentedTest {

    @get:Rule
    var activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Before
    fun setUp() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.getEspressoIdlingResource())
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.getEspressoIdlingResource())
    }

    @Test
    fun loadMovies() {
        Espresso.onView(withId(R.id.rv_user_movie)).apply {
            check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
            perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(19))
        }
    }

    @Test
    fun loadDetailMovie() {
        Espresso.onView(withId(R.id.rv_user_movie)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                ViewActions.click()
            )
        )
        Espresso.onView(withId(R.id.title))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.overview))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.year))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.score))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun loadTv() {
        Espresso.onView(ViewMatchers.withText("TV SHOWS")).perform(ViewActions.click())
        Espresso.onView(withId(R.id.rv_user_tv)).apply {
            check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
            perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(19))
        }
    }

    @Test
    fun loadDetailTv() {
        Espresso.onView(ViewMatchers.withText("TV SHOWS")).perform(ViewActions.click())
        Espresso.onView(withId(R.id.rv_user_tv)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                ViewActions.click()
            )
        )
        Espresso.onView(withId(R.id.title))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.overview))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.year))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.score))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun loadFavMovie(){
        addFavMovie()
        Espresso.pressBack()
        Espresso.onView(withId(R.id.btn_favorite)).perform(ViewActions.click())
        Espresso.onView(withId(R.id.rv_favorite_movie)).apply {
            check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
            perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(19))
        }
    }

    @Test
    fun loadDetailFavMovie(){
        Espresso.onView(withId(R.id.btn_favorite)).perform(ViewActions.click())
        Espresso.onView(withId(R.id.rv_favorite_movie)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                ViewActions.click()
            )
        )
        Espresso.onView(withId(R.id.title))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.overview))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.year))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.score))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun loadFavTv(){
        addFavTv()
        Espresso.pressBack()
        Espresso.onView(withId(R.id.btn_favorite)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withText("TV SHOWS")).perform(ViewActions.click())
        Espresso.onView(withId(R.id.rv_favorite_tv)).apply {
            check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
            perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(19))
        }
    }

    @Test
    fun loadDetailFavTv() {
        addFavTv()
        Espresso.pressBack()
        Espresso.onView(withId(R.id.btn_favorite)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withText("TV SHOWS")).perform(ViewActions.click())
        Espresso.onView(withId(R.id.rv_favorite_tv)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                ViewActions.click()
            )
        )
        Espresso.onView(withId(R.id.title))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.overview))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.year))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.score))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun addFavMovie(){
        Espresso.onView(withId(R.id.rv_user_movie)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                ViewActions.click()
            )
        )
        Espresso.onView(withId(R.id.action_favorite)).perform(ViewActions.click())
        Espresso.pressBack()
        Espresso.onView(withId(R.id.rv_user_movie)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                1,
                ViewActions.click()
            )
        )
        Espresso.onView(withId(R.id.action_favorite)).perform(ViewActions.click())
        Espresso.pressBack()
        Espresso.onView(withId(R.id.rv_user_movie)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                2,
                ViewActions.click()
            )
        )
        Espresso.onView(withId(R.id.action_favorite)).perform(ViewActions.click())
    }

    @Test
    fun addFavTv(){
        Espresso.onView(ViewMatchers.withText("TV SHOWS")).perform(ViewActions.click())
        Espresso.onView(withId(R.id.rv_user_tv)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                ViewActions.click()
            )
        )
        Espresso.onView(withId(R.id.action_favorite)).perform(ViewActions.click())
        Espresso.pressBack()
        Espresso.onView(withId(R.id.rv_user_tv)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                1,
                ViewActions.click()
            )
        )
        Espresso.onView(withId(R.id.action_favorite)).perform(ViewActions.click())
        Espresso.pressBack()
        Espresso.onView(withId(R.id.rv_user_tv)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                2,
                ViewActions.click()
            )
        )
        Espresso.onView(withId(R.id.action_favorite)).perform(ViewActions.click())
    }

    @Test
    fun removeFavMovie(){
        addFavMovie()
        Espresso.pressBack()
        Espresso.onView(withId(R.id.btn_favorite)).perform(ViewActions.click())
        Espresso.onView(withId(R.id.rv_favorite_movie)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                ViewActions.click()
            )
        )
        Espresso.onView(withId(R.id.action_favorite)).perform(ViewActions.click())
    }

    @Test
    fun removeFavTv(){
        addFavTv()
        Espresso.pressBack()
        Espresso.onView(withId(R.id.btn_favorite)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withText("TV SHOWS")).perform(ViewActions.click())
        Espresso.onView(withId(R.id.rv_favorite_tv)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                ViewActions.click()
            )
        )
        Espresso.onView(withId(R.id.action_favorite)).perform(ViewActions.click())
    }

}