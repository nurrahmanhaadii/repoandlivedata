package id.haadii.submission.dicoding.repoandlivedata.detail

import android.app.Application
import android.util.Log
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import id.haadii.submission.dicoding.repoandlivedata.movie.DataItemMovie
import id.haadii.submission.dicoding.repoandlivedata.repository.EndPoint.Companion.IMG_URL
import id.haadii.submission.dicoding.repoandlivedata.repository.local.LocalRepository
import id.haadii.submission.dicoding.repoandlivedata.repository.local.entity.MovieEntity
import id.haadii.submission.dicoding.repoandlivedata.repository.local.entity.TvShowEntity
import id.haadii.submission.dicoding.repoandlivedata.tvshow.DataItemTv

class DetailViewModel(application: Application, private val repository: LocalRepository) : AndroidViewModel(application) {
    private lateinit var movie : DataItemMovie
    private lateinit var tvShow: DataItemTv

    val title = MutableLiveData<String>()
    val date = MutableLiveData<String>()
    val runtime = MutableLiveData<String>()
    val overview = MutableLiveData<String>()
    val score = MutableLiveData<String>()
    val imageUrl = MutableLiveData<String>()
    val photoUrl = MutableLiveData<String>()

    val favoriteMovies: LiveData<List<MovieEntity>> = repository.getMovie()
    val favoriteTvShows: LiveData<List<TvShowEntity>> = repository.getTvShow()

    private var movieEntity : MovieEntity? = null
    private var tvEntity : TvShowEntity? = null

    var isFavorite : Boolean = false

    companion object {
        @JvmStatic
        @BindingAdapter("imageUrl")
        fun setImageUrl(imageView: ImageView, url: String?) {
            Glide.with(imageView)
                .load(url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imageView)
        }
    }

    private fun getMovie() : DataItemMovie{
        return movie
    }

    private fun getTvShow() : DataItemTv {
        return tvShow
    }

    fun setMovie(movie: DataItemMovie) {
        this.movie = movie
    }

    fun setDetailMovie() {
        title.value = getMovie().title
        date.value = getMovie().release_date
        runtime.value = "Popularity: ${getMovie().popularity}"
        overview.value = getMovie().overview
        score.value = getMovie().vote_count
        imageUrl.value = IMG_URL + getMovie().poster_path
        photoUrl.value = IMG_URL + getMovie().backdrop_path
    }

    fun setTvShow(tvShow: DataItemTv) {
        this.tvShow = tvShow
    }

    fun setDetailTvShow() {
        title.value = getTvShow().name
        date.value = getTvShow().first_air_date
        runtime.value = "Popularity: ${getTvShow().popularity}"
        overview.value = getTvShow().overview
        score.value = getTvShow().vote_count
        imageUrl.value = IMG_URL + getTvShow().poster_path
        photoUrl.value = IMG_URL + getTvShow().backdrop_path
    }

    fun setFavoriteMovie(movieEntity: MovieEntity) {
        repository.insertMovie(movieEntity)
    }

    fun setUnfavoriteMovie(movieEntity: MovieEntity) {
        repository.deleteMovie(movieEntity)
    }

    fun setFavoriteTvShow(tvShowEntity: TvShowEntity) {
        repository.insertTvShow(tvShowEntity)
    }

    fun setUnFavoriteTvShow(tvShowEntity: TvShowEntity) {
        repository.deleteTvShow(tvShowEntity)
    }

    fun setMovieEntity (movie: DataItemMovie) {
        movieEntity = MovieEntity(
            id = movie.id,
            title = movie.title,
            release_date = movie.release_date,
            popularity = movie.popularity,
            backdrop_path = movie.backdrop_path,
            poster_path = movie.poster_path,
            overview = movie.overview,
            vote_count = movie.vote_count
        )
    }

    fun setTvShowEntity (tv: DataItemTv) {
        tvEntity = TvShowEntity(
            id = tv.id,
            name = tv.name,
            first_air_date = tv.first_air_date,
            popularity = tv.popularity,
            overview = tv.overview,
            vote_count = tv.vote_count,
            poster_path = tv.poster_path,
            backdrop_path = tv.backdrop_path
        )
    }

    fun getMovieEntity() : MovieEntity {
        return movieEntity!!
    }

    fun getTvShowEntity() : TvShowEntity {
        return tvEntity!!
    }

    fun checkFavoriteMovie(movie: DataItemMovie, list: List<MovieEntity>) {
        for (movieEntity in list) {
            if (movie.id == movieEntity.id) {
                isFavorite = true
                break
            }
        }
    }

    fun checkFavoriteTv(tv: DataItemTv, list: List<TvShowEntity>) {
        for (tvEntity in list ) {
            if (tv.id == tvEntity.id) {
                isFavorite = true
                break
            }
        }
    }
}