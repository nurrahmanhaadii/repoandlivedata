package id.haadii.submission.dicoding.repoandlivedata.tvshow

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.rule.ActivityTestRule
import id.haadii.submission.dicoding.repoandlivedata.R
import id.haadii.submission.dicoding.repoandlivedata.main.MainActivity
import id.haadii.submission.dicoding.repoandlivedata.utils.EspressoIdlingResource
import id.haadii.submission.dicoding.repoandlivedata.utils.SelectTabIndex
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class TvShowFragmentTest {
    @get:Rule
    val activityTestRule = ActivityTestRule<MainActivity>(MainActivity::class.java)

    @Before
    fun setUp() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.getEspressoIdlingResource())
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.getEspressoIdlingResource())
    }

    @Test
    fun loadTvShow() {
        onView(withId(R.id.tab_layout)).perform(SelectTabIndex.selectTabAtPosition(1))
        onView(withId(R.id.rv_tv_show)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_tv_show)).perform(RecyclerViewActions.actionOnItemAtPosition<TvShowAdapter.TvShowAdapterViewHolder>(0, click()))
        onView(withId(R.id.img_background)).check(matches(isDisplayed()))
        onView(withId(R.id.img_photo_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_tittle_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_date_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_runtime)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_overview_content)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_genre_content)).check(matches(isDisplayed()))
    }
}