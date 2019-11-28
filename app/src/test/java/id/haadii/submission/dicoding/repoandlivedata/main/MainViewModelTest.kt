package id.haadii.submission.dicoding.repoandlivedata.main

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.mock
import id.haadii.submission.dicoding.repoandlivedata.movie.DataItemMovie
import id.haadii.submission.dicoding.repoandlivedata.movie.Movie
import id.haadii.submission.dicoding.repoandlivedata.repository.network.NetworkRepository
import id.haadii.submission.dicoding.repoandlivedata.tvshow.DataItemTv
import id.haadii.submission.dicoding.repoandlivedata.tvshow.TvShow
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MainViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: MainViewModel
    private var repository = mock(NetworkRepository::class.java)

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        viewModel = MainViewModel(repository)
    }

    @Test
    fun getMovies() {
        val results = ArrayList<DataItemMovie>()
        results.add(DataItemMovie(id = 34, title = "Movie Title", release_date = "11/11/2019", overview = "overview", vote_count = "123", popularity = "123", poster_path = "poster_path.png", backdrop_path = "backdrop_path.jpg"))
        val dummyMovies = Movie(results)
        val movies = MutableLiveData<Movie>()
        movies.value = dummyMovies

        `when`(repository.getAllMovies(com.nhaarman.mockitokotlin2.any())).thenAnswer {
            (it.arguments[1] as (Movie?) -> Unit).invoke(dummyMovies)
        }

        val observer= mock(Observer::class.java) as Observer<Movie>
        viewModel.setMovie().value = dummyMovies
        viewModel.setMovie().observeForever(observer)
        verify(observer, times(1)).onChanged(dummyMovies)
        assertNotNull(viewModel.setMovie().value)
        assertEquals(results[0].id, viewModel.setMovie().value?.results?.get(0)?.id)
    }

    @Test
    fun getTvShows() {
        val results = ArrayList<DataItemTv>()
        results.add(DataItemTv(id = 29, name = "Tv Show Title", first_air_date = "11/11/2019", overview = "overview", vote_count = "321", popularity = "321", poster_path = "poster_path.png", backdrop_path = "backdrop_path.jpg"))
        val dummyTvShows = TvShow(results)
        val tvShows = MutableLiveData<TvShow>()
        tvShows.value = dummyTvShows

        `when`(repository.getAllTvShow(com.nhaarman.mockitokotlin2.any())).thenAnswer {
            (it.arguments[1] as (TvShow?) -> Unit).invoke(dummyTvShows)
        }

        val observer = mock() as Observer<TvShow>
        viewModel.setTvShow().value = dummyTvShows
        viewModel.setTvShow().observeForever(observer)
        verify(observer, times(1)).onChanged(dummyTvShows)
        assertNotNull(viewModel.setTvShow().value)
        assertEquals(results[0].id, viewModel.setTvShow().value?.results?.get(0)?.id)
    }
}
