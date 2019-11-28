package id.haadii.submission.dicoding.repoandlivedata.movie

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import id.haadii.submission.dicoding.repoandlivedata.R
import id.haadii.submission.dicoding.repoandlivedata.repository.EndPoint
import id.haadii.submission.dicoding.repoandlivedata.repository.local.entity.MovieEntity
import kotlinx.android.synthetic.main.item_movie.view.*

class MoviePagedListAdapter(private val listener: (DataItemMovie) -> Unit) :
    PagedListAdapter<MovieEntity, MoviePagedListAdapter.MoviePagedHolder>(diffCallback) {

    private lateinit var dataItemMovie: DataItemMovie

    companion object {
        private val diffCallback: DiffUtil.ItemCallback<MovieEntity> = object : DiffUtil.ItemCallback<MovieEntity>() {
            override fun areContentsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean {
                return oldItem.title == newItem.title
            }

            override fun areItemsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviePagedHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        return MoviePagedHolder(view)
    }

    override fun onBindViewHolder(holder: MoviePagedHolder, position: Int) {
        val movieEntity = getItem(position)
        if (movieEntity != null) {
            setDataItemMovie(movieEntity)
            holder.bind(dataItemMovie, listener)
        }
    }

    inner class MoviePagedHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(item: DataItemMovie, listener: (DataItemMovie) -> Unit) {
            Log.e("item", "$item")
            Glide.with(itemView.context)
                .load(EndPoint.IMG_URL + item.poster_path)
                .into(itemView.img_photo)
            itemView.tv_title.text = item.title
            itemView.tv_date.text = item.release_date
            itemView.tv_overview.text = item.overview
            itemView.setOnClickListener {
                listener(item)
            }
        }
    }

    private fun setDataItemMovie(movieEntity: MovieEntity) {
        dataItemMovie = DataItemMovie(
            id = movieEntity.id,
            title = movieEntity.title,
            release_date = movieEntity.release_date,
            popularity = movieEntity.popularity,
            backdrop_path = movieEntity.backdrop_path,
            poster_path = movieEntity.poster_path,
            overview = movieEntity.overview,
            vote_count = movieEntity.vote_count
        )
    }
}