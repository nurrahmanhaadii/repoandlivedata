package id.haadii.submission.dicoding.repoandlivedata.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import id.haadii.submission.dicoding.repoandlivedata.movie.DataItemMovie
import id.haadii.submission.dicoding.repoandlivedata.movie.Movie
import id.haadii.submission.dicoding.repoandlivedata.repository.local.LocalRepository
import id.haadii.submission.dicoding.repoandlivedata.repository.local.entity.MovieEntity
import id.haadii.submission.dicoding.repoandlivedata.repository.local.entity.TvShowEntity
import id.haadii.submission.dicoding.repoandlivedata.repository.network.NetworkRepository
import id.haadii.submission.dicoding.repoandlivedata.tvshow.DataItemTv
import id.haadii.submission.dicoding.repoandlivedata.tvshow.TvShow

class MainViewModel(private val repository: NetworkRepository, private val localRepository: LocalRepository) : ViewModel() {

    private var dataMovie = MutableLiveData<Movie>()
    private var dataTvShow = MutableLiveData<TvShow>()

    private var favoriteMovies : LiveData<List<MovieEntity>>
    private var favoriteTvShow: LiveData<List<TvShowEntity>>

    var favoriteMovieObserver = MutableLiveData<ArrayList<DataItemMovie>>()
    var favoriteTvShowObserver = MutableLiveData<ArrayList<DataItemTv>>()

    private var favoriteMovieList = ArrayList<DataItemMovie>()
    private var favoriteTvShowList = ArrayList<DataItemTv>()

    init {
        getMovie()
        getTvShow()
        favoriteMovies = localRepository.getMovie()
        favoriteTvShow = localRepository.getTvShow()
    }

    fun getMovieList() : LiveData<PagedList<MovieEntity>> {
        return LivePagedListBuilder<Int, MovieEntity>(localRepository.getAllMovie(), 10).build()
    }

    private fun getMovie() {
        repository.getAllMovies {
            if (it != null) {
                dataMovie.value = it
            } else {
                dataMovie.value = it
            }
        }


    }

    private fun getTvShow() {
        repository.getAllTvShow {
            if (it != null) {
                dataTvShow.value = it
            }
        }
    }

    fun setMovie() : MutableLiveData<Movie> {
        return dataMovie
    }

    fun setTvShow() : MutableLiveData<TvShow> {
        return dataTvShow
    }

    fun getFavoriteMovie() : LiveData<List<MovieEntity>> {
        return favoriteMovies
    }

    fun getFavoriteTvShow() : LiveData<List<TvShowEntity>> {
        return favoriteTvShow
    }

    fun setFavoriteMovie(movieEntity: List<MovieEntity>) {
        favoriteMovieList.clear()
        for (item in movieEntity) {
            val favoriteMovie = DataItemMovie(
                id = item.id,
                title = item.title,
                release_date = item.release_date,
                overview = item.overview,
                popularity = item.popularity,
                vote_count = item.vote_count,
                poster_path = item.poster_path,
                backdrop_path = item.backdrop_path)

            favoriteMovieList.add(favoriteMovie)
        }
        favoriteMovieObserver.postValue(favoriteMovieList)
    }

    fun setFavoriteTvShow(tvShowEntity: List<TvShowEntity>) {
        favoriteTvShowList.clear()
        for (tv in tvShowEntity) {
            val favoriteTv = DataItemTv(
                id = tv.id,
                name = tv.name,
                first_air_date = tv.first_air_date,
                popularity = tv.popularity,
                overview = tv.overview,
                vote_count = tv.vote_count,
                poster_path = tv.poster_path,
                backdrop_path = tv.backdrop_path
            )
            favoriteTvShowList.add(favoriteTv)
        }
        favoriteTvShowObserver.postValue(favoriteTvShowList)
    }

}