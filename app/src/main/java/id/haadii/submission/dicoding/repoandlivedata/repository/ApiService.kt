package id.haadii.submission.dicoding.repoandlivedata.repository

import id.haadii.submission.dicoding.repoandlivedata.movie.Movie
import id.haadii.submission.dicoding.repoandlivedata.tvshow.TvShow
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("movie")
    fun movie(@Query("api_key") value: String,
              @Query("language") lang: String,
              @Query("page") page: Int) : Call<Movie>

    @GET("tv")
    fun tv(@Query("api_key") value: String,
           @Query("language") lang: String) : Call<TvShow>
}