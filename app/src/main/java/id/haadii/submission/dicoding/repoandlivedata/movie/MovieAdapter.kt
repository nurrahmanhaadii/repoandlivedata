package id.haadii.submission.dicoding.repoandlivedata.movie

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import id.haadii.submission.dicoding.repoandlivedata.R
import id.haadii.submission.dicoding.repoandlivedata.repository.EndPoint.Companion.IMG_URL
import kotlinx.android.synthetic.main.item_movie.view.*
import java.util.*

class MovieAdapter(private val items: ArrayList<DataItemMovie>, private val listener: (DataItemMovie) -> Unit) :
    RecyclerView.Adapter<MovieAdapter.MovieAdapterViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieAdapterViewHolder {
        return MovieAdapterViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_movie,
                parent,
                false
            )
        )
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: MovieAdapterViewHolder, position: Int) {
        holder.bind(items[position], listener)
    }

    class MovieAdapterViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bind(item: DataItemMovie, listener: (DataItemMovie) -> Unit) {

            Glide.with(itemView.context)
                .load(IMG_URL + item.poster_path)
                .into(itemView.img_photo)
            itemView.tv_title.text = item.title
            itemView.tv_date.text = item.release_date
            itemView.tv_overview.text = item.overview
            itemView.setOnClickListener {
                listener(item)
            }
        }
    }
}