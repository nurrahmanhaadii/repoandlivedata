package id.haadii.submission.dicoding.repoandlivedata.tvshow

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import id.haadii.submission.dicoding.repoandlivedata.R
import id.haadii.submission.dicoding.repoandlivedata.repository.EndPoint.Companion.IMG_URL
import kotlinx.android.synthetic.main.item_tv_show.view.*
import java.util.*

class TvShowAdapter(private val items: ArrayList<DataItemTv>, private val listener: (DataItemTv) -> Unit) :
    RecyclerView.Adapter<TvShowAdapter.TvShowAdapterViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvShowAdapterViewHolder {
        return TvShowAdapterViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_tv_show,
                parent,
                false
            )
        )
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: TvShowAdapterViewHolder, position: Int) {
        holder.bind(items[position], listener)
    }

    class TvShowAdapterViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bind(item: DataItemTv, listener: (DataItemTv) -> Unit) {
            Glide.with(itemView.context)
                .load(IMG_URL + item.poster_path)
                .into(itemView.img_photo)
            itemView.tv_title.text = item.name
            itemView.tv_date.text = item.first_air_date
            itemView.tv_overview.text = item.overview
            itemView.setOnClickListener {
                listener(item)
            }
        }
    }
}