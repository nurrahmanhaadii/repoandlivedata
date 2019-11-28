package id.haadii.submission.dicoding.repoandlivedata.repository.local.dao

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import id.haadii.submission.dicoding.repoandlivedata.repository.local.entity.MovieEntity

@Dao
interface MovieDao {
    @Query("SELECT * FROM movie_items")
    fun getAll() : LiveData<List<MovieEntity>>

    @Query("SELECT * FROM movie_items ORDER BY id ASC")
    fun getMovies() : DataSource.Factory<Int, MovieEntity>

    @Insert
    fun insert(vararg movieEntity: MovieEntity)

    @Delete
    fun delete(movieEntity: MovieEntity)

    @Update
    fun update(vararg movieEntity: MovieEntity)
}