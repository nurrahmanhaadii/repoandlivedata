package id.haadii.submission.dicoding.repoandlivedata.detail

import android.content.Intent
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import id.haadii.submission.dicoding.repoandlivedata.R
import id.haadii.submission.dicoding.repoandlivedata.movie.DataItemMovie
import id.haadii.submission.dicoding.repoandlivedata.utils.EspressoIdlingResource
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class DetailActivityTest {

    val movie = DataItemMovie(
        id = 290859, title = "Terminator: Dark Fate", release_date = "2019-10-23", vote_count = "511",
        popularity = "270.155", poster_path = "/vqzNJRH4YyquRiWxCCOH0aXggHI.jpg", backdrop_path = "/a6cDxdwaQIFjSkXf7uskg78ZyTq.jpg",
        overview = "More than two decades have passed since Sarah Connor prevented Judgment Day, changed the future, and re-wrote the fate of the human race. Dani Ramos is living a simple life in Mexico City with her brother and father when a highly advanced and deadly new Terminator – a Rev-9 – travels back through time to hunt and kill her. Dani's survival depends on her joining forces with two warriors: Grace, an enhanced super-soldier from the future, and a battle-hardened Sarah Connor. As the Rev-9 ruthlessly destroys everything and everyone in its path on the hunt for Dani, the three are led to a T-800 from Sarah’s past that may be their last best hope."
    )
    @get:Rule
    val activityRule = object : ActivityTestRule<DetailActivity>(DetailActivity::class.java) {
        override fun getActivityIntent(): Intent {
            val context = InstrumentationRegistry.getInstrumentation().targetContext
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra("movie", movie)
            return intent
        }
    }

    @Before
    fun setUp() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.getEspressoIdlingResource())
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.getEspressoIdlingResource())
    }

    @Test
    fun loadMovie() {
        onView(withId(R.id.img_background)).check(matches(isDisplayed()))
        onView(withId(R.id.img_photo_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_tittle_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_date_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_runtime)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_overview_content)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_genre_content)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_tittle_detail)).check(matches(withText("Terminator: Dark Fate")))
    }
}