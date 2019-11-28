package id.haadii.submission.dicoding.repoandlivedata.repository.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import id.haadii.submission.dicoding.repoandlivedata.repository.local.dao.MovieDao
import id.haadii.submission.dicoding.repoandlivedata.repository.local.dao.TvShowDao
import id.haadii.submission.dicoding.repoandlivedata.repository.local.entity.MovieEntity
import id.haadii.submission.dicoding.repoandlivedata.repository.local.entity.TvShowEntity

@Database(
    entities = [MovieEntity::class, TvShowEntity::class],
    version = 1
)
abstract class FavoriteDatabase : RoomDatabase() {
    abstract fun MovieDao() : MovieDao
    abstract fun TvShowDao() : TvShowDao

    companion object {
        @Volatile private var instance: FavoriteDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDataBase(context).also { instance = it }
        }

        private fun buildDataBase(context: Context) = Room.databaseBuilder(context,
            FavoriteDatabase::class.java, "movie-catalogue.db")
            .build()
    }
}