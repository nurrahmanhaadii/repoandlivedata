package id.haadii.submission.dicoding.repoandlivedata.repository.local.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "movie_items")
data class MovieEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    @ColumnInfo(name = "title")
    var title: String,
    @ColumnInfo(name = "release_date")
    var release_date: String,
    @ColumnInfo(name = "overview")
    var overview: String,
    @ColumnInfo(name = "vote_count")
    var vote_count: String,
    @ColumnInfo(name = "popularity")
    var popularity: String,
    @ColumnInfo(name = "poster_path")
    var poster_path: String,
    @ColumnInfo(name = "backdrop_path")
    var backdrop_path: String
) : Parcelable