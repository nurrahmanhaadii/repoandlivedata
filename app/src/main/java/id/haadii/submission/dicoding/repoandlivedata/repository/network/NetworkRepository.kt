package id.haadii.submission.dicoding.repoandlivedata.repository.network

import android.os.Handler
import androidx.paging.ItemKeyedDataSource
import androidx.paging.PageKeyedDataSource
import id.haadii.submission.dicoding.repoandlivedata.BuildConfig
import id.haadii.submission.dicoding.repoandlivedata.movie.DataItemMovie
import id.haadii.submission.dicoding.repoandlivedata.movie.Movie
import id.haadii.submission.dicoding.repoandlivedata.repository.NetworkConfig
import id.haadii.submission.dicoding.repoandlivedata.tvshow.DataItemTv
import id.haadii.submission.dicoding.repoandlivedata.tvshow.TvShow
import id.haadii.submission.dicoding.repoandlivedata.utils.EspressoIdlingResource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NetworkRepository {

    fun getAllMovies(listener : (Movie?) -> Unit) {
        EspressoIdlingResource.increment()
        val handler = Handler()
        handler.postDelayed({
            NetworkConfig.api()
                .movie(BuildConfig.API_KEY, "en-US", 1).enqueue(object : Callback<Movie> {
                val result = ArrayList<DataItemMovie>()
                override fun onFailure(call: Call<Movie>, t: Throwable) {
                    listener(Movie(result))
                    EspressoIdlingResource.decrement()
                }

                override fun onResponse(call: Call<Movie>, response: Response<Movie>) {
                    if (response.isSuccessful) {
                        listener(response.body())
                    } else {
                        listener(Movie(result))
                    }
                    EspressoIdlingResource.decrement()
                }
            })
        }, 5000)
    }

    fun movieLoadBefore(param: ItemKeyedDataSource.LoadParams<Int>) {

    }

    fun movieLoadAfter(param: ItemKeyedDataSource.LoadParams<Int>, callback: PageKeyedDataSource.LoadCallback<Int, Movie>) {
        NetworkConfig.api().movie(BuildConfig.API_KEY, "en-US", param.key).enqueue(object : Callback<Movie> {
            override fun onResponse(call: Call<Movie>, response: Response<Movie>) {
                if (response.isSuccessful) {

                }
            }

            override fun onFailure(call: Call<Movie>, t: Throwable) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        })

    }

    fun getAllTvShow(listener: (TvShow?) -> Unit) {
        EspressoIdlingResource.increment()
        val handler = Handler()
        handler.postDelayed({
            NetworkConfig.api()
                .tv(BuildConfig.API_KEY, "en-US").enqueue(object : Callback<TvShow> {
                val result = ArrayList<DataItemTv>()
                override fun onFailure(call: Call<TvShow>, t: Throwable) {
                    listener(TvShow(result))
                    EspressoIdlingResource.decrement()
                }

                override fun onResponse(call: Call<TvShow>, response: Response<TvShow>) {
                    if (response.isSuccessful) {
                        listener(response.body())
                    } else {
                        listener(TvShow(result))
                    }
                    EspressoIdlingResource.decrement()
                }
            })
        }, 5000)
    }
}