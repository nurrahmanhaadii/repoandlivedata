package id.haadii.submission.dicoding.repoandlivedata.detail

import android.app.Application
import id.haadii.submission.dicoding.repoandlivedata.movie.DataItemMovie
import id.haadii.submission.dicoding.repoandlivedata.tvshow.DataItemTv
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.mockito.Mock

class DetailViewModelTest {
    private lateinit var viewModel: DetailViewModel

    private val movie = DataItemMovie(
        id = 290859, title = "Terminator: Dark Fate", release_date = "2019-10-23", vote_count = "511",
        popularity = "270.155", poster_path = "/vqzNJRH4YyquRiWxCCOH0aXggHI.jpg", backdrop_path = "/a6cDxdwaQIFjSkXf7uskg78ZyTq.jpg",
        overview = "More than two decades have passed since Sarah Connor prevented Judgment Day, changed the future, and re-wrote the fate of the human race. Dani Ramos is living a simple life in Mexico City with her brother and father when a highly advanced and deadly new Terminator – a Rev-9 – travels back through time to hunt and kill her. Dani's survival depends on her joining forces with two warriors: Grace, an enhanced super-soldier from the future, and a battle-hardened Sarah Connor. As the Rev-9 ruthlessly destroys everything and everyone in its path on the hunt for Dani, the three are led to a T-800 from Sarah’s past that may be their last best hope."
    )

    private val tv = DataItemTv(
        id = 60625, name = "Rick and Morty", first_air_date = "2013-12-02", vote_count = "1462",
        popularity = "525.108", poster_path = "/qJdfO3ahgAMf2rcmhoqngjBBZW1.jpg", backdrop_path = "/mzzHr6g1yvZ05Mc7hNj3tUdy2bM.jpg",
        overview = "Rick is a mentally-unbalanced but scientifically-gifted old man who has recently reconnected with his family. He spends most of his time involving his young grandson Morty in dangerous, outlandish adventures throughout space and alternate universes. Compounded with Morty's already unstable family life, these events cause Morty much distress at home and school."
    )

    @Mock
    val application = Application()

    @Before
    fun setUp() {
        viewModel = DetailViewModel(application)
    }

    @Test
    fun getMovie() {
        assertNotNull(viewModel.setMovie(movie))
        assertEquals(movie.title, "Terminator: Dark Fate")
    }

    @Test
    fun getTvShow() {
        assertNotNull(viewModel.setTvShow(tv))
        assertEquals(tv.name, "Rick and Morty")
    }

}