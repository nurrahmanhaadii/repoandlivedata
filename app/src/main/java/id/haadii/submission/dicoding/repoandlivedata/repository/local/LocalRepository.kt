package id.haadii.submission.dicoding.repoandlivedata.repository.local

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.LiveData
import id.haadii.submission.dicoding.repoandlivedata.repository.local.dao.MovieDao
import id.haadii.submission.dicoding.repoandlivedata.repository.local.dao.TvShowDao
import id.haadii.submission.dicoding.repoandlivedata.repository.local.entity.MovieEntity
import id.haadii.submission.dicoding.repoandlivedata.repository.local.entity.TvShowEntity
import javax.sql.DataSource

class LocalRepository(application: Application) {
    private var movieDao : MovieDao
    private var allMovie : LiveData<List<MovieEntity>>

    private var tvDao : TvShowDao
    private var allTvShow : LiveData<List<TvShowEntity>>

    init {
        val db = FavoriteDatabase(application)
        movieDao = db.MovieDao()
        allMovie = movieDao.getAll()
        tvDao = db.TvShowDao()
        allTvShow = tvDao.getAll()
    }

    fun getMovie(): LiveData<List<MovieEntity>> {
        return allMovie
    }

    fun getAllMovie() : androidx.paging.DataSource.Factory<Int, MovieEntity> {
        return movieDao.getMovies()
    }

    fun insertMovie(movieEntity: MovieEntity) {
        InsertMovieAsyncTask(movieDao).execute(movieEntity)
    }

    fun deleteMovie(movieEntity: MovieEntity) {
        DeleteMovieAsyncTask(movieDao).execute(movieEntity)
    }

    fun getTvShow(): LiveData<List<TvShowEntity>> {
        return allTvShow
    }

    fun insertTvShow(tvShowEntity: TvShowEntity) {
        InsertTvShowAsyncTask(tvDao).execute(tvShowEntity)
    }

    fun deleteTvShow(tvShowEntity: TvShowEntity) {
        DeleteTvShowAsyncTask(tvDao).execute(tvShowEntity)
    }

    inner class InsertTvShowAsyncTask internal constructor(dao: TvShowDao): AsyncTask<TvShowEntity, Void, Void>() {
        private val mAsyncTask: TvShowDao = dao

        override fun doInBackground(vararg p0: TvShowEntity): Void? {
            mAsyncTask.insert(p0[0])
            return null
        }
    }

    inner class DeleteTvShowAsyncTask internal constructor(dao: TvShowDao): AsyncTask<TvShowEntity, Void, Void>() {
        private val mAsyncTask: TvShowDao = dao

        override fun doInBackground(vararg p0: TvShowEntity): Void? {
            mAsyncTask.delete(p0[0])
            return null
        }
    }

    inner class InsertMovieAsyncTask internal constructor(dao: MovieDao): AsyncTask<MovieEntity, Void, Void>() {
        private val mAsyncTaskDao: MovieDao = dao

        override fun doInBackground(vararg params: MovieEntity): Void? {
            mAsyncTaskDao.insert(params[0])
            return null
        }
    }

    inner class DeleteMovieAsyncTask internal constructor(dao: MovieDao): AsyncTask<MovieEntity, Void, Void>() {
        private val mAsyncTask: MovieDao = dao

        override fun doInBackground(vararg p0: MovieEntity): Void? {
            mAsyncTask.delete(p0[0])
            return null
        }
    }
}