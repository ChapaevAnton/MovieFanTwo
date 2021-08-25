package com.w4ereT1ckRtB1tch.moviefan

import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.w4ereT1ckRtB1tch.moviefan.data.DataBase
import com.w4ereT1ckRtB1tch.moviefan.ui.home.HomeCatalogFilmAdapter
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.not
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @get:Rule
    val activityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun homeRecyclerCatalogFilmShouldBeAttached() {
        onView(withId(R.id.home_recycler_catalog_film)).check(matches(isDisplayed()))
        onView(withId(R.id.home_recycler_catalog_film)).perform(
            RecyclerViewActions.actionOnItemAtPosition<HomeCatalogFilmAdapter.ItemFilmHolder>(
                0,
                click()
            )
        )
    }


    @Test
    fun allNavigationBottomMenuDestinationsShouldWork() {
        onView(withId(R.id.main_menu_home)).perform(click())
        onView(withId(R.id.home_fragment_root)).check(matches(isDisplayed()))

        onView(withId(R.id.my_selections)).perform(click())
        onView(withId(R.id.selections_fragment_root)).check(matches(isDisplayed()))

        onView(withId(R.id.favorites)).perform(click())
        onView(withId(R.id.favorites_fragment_root)).check(matches(isDisplayed()))
    }

    @Test
    fun viewSearchAbleToEnterSearchTextAndCheckingTheSearchResult() {

        onView(withId(R.id.my_selections)).perform(click())
        onView(withId(R.id.selections_fragment_root)).check(matches(isDisplayed()))

        onView(withId(R.id.selections_search_top_bar)).check(matches(isDisplayed()))
        onView(withId(R.id.selections_search_top_bar)).perform(typeViewSearchText(DataBase.filmDataBase[2].title))

        onView(withId(R.id.selections_recycler_catalog_film)).check(matches(isDisplayed()))
        onView(withId(R.id.selections_recycler_catalog_film)).perform(
            RecyclerViewActions.actionOnItemAtPosition<HomeCatalogFilmAdapter.ItemFilmHolder>(
                0,
                click()
            )
        )
    }

    @Test
    fun shouldOpenDetailsFragmentAndSwipeUpDownView() {
        onView(withId(R.id.home_recycler_catalog_film)).check(matches(isDisplayed()))
        onView(withId(R.id.home_recycler_catalog_film)).perform(
            RecyclerViewActions.actionOnItemAtPosition<HomeCatalogFilmAdapter.ItemFilmHolder>(
                0,
                click()
            )
        )
        onView(withId(R.id.film_details_fragment_root)).check(matches(isDisplayed()))
        onView(withId(R.id.details_description_film)).check(matches(isDisplayed()))
        onView(withId(R.id.details_description_film)).perform(swipeUp())
        onView(withId(R.id.details_description_film)).perform(swipeDown())
    }


    @Test
    fun addFilmToFavoritesButtonClickable() {
        onView(withId(R.id.home_recycler_catalog_film)).check(matches(isDisplayed()))
        onView(withId(R.id.home_recycler_catalog_film)).perform(
            RecyclerViewActions.actionOnItemAtPosition<HomeCatalogFilmAdapter.ItemFilmHolder>(
                0,
                click()
            )
        )
        onView(withId(R.id.details_film_fab)).check(matches(isDisplayed()))
        onView(withId(R.id.details_favorites_film_fab)).check(matches(not(isDisplayed())))
        onView(withId(R.id.details_film_fab)).perform(click())

        onView(withId(R.id.details_favorites_film_fab)).check(matches(isDisplayed()))
        onView(withId(R.id.details_favorites_film_fab)).check(matches(isEnabled()))
        onView(withId(R.id.details_favorites_film_fab)).perform(click())

        onView(withId(R.id.details_film_fab)).perform(click())
        onView(withId(R.id.details_favorites_film_fab)).check(matches(not(isDisplayed())))
    }

    //ввод текста в SearchView
    private fun typeViewSearchText(textQuery: String?): ViewAction? {
        return object : ViewAction {
            override fun getConstraints(): Matcher<View> {
                return allOf(isDisplayed(), isAssignableFrom(SearchView::class.java))
            }

            override fun getDescription(): String {
                return "Search query text"
            }

            override fun perform(uiController: UiController?, view: View?) {
                val v = view as SearchView
                v.setQuery(textQuery, false)
            }
        }
    }

}