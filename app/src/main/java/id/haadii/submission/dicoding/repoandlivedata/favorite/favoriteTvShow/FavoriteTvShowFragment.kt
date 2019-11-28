package id.haadii.submission.dicoding.repoandlivedata.favorite.favoriteTvShow


import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import id.haadii.submission.dicoding.repoandlivedata.R
import id.haadii.submission.dicoding.repoandlivedata.main.MainViewModel
import kotlinx.android.synthetic.main.fragment_tv_show.*
import java.util.*

import id.haadii.submission.dicoding.repoandlivedata.tvshow.TvShowAdapter
import id.haadii.submission.dicoding.repoandlivedata.detail.DetailActivity

class FavoriteTvShowFragment : Fragment() {

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_tv_show, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(Objects.requireNonNull(activity!!)).get(MainViewModel::class.java)

        initObserver()
    }

    private fun initObserver() {
        viewModel.favoriteTvShowObserver.observe(this, androidx.lifecycle.Observer {
            if (it.isNotEmpty()) {
                tv_tv_show_empty.visibility = View.GONE
                rv_tv_show.apply {
                    setHasFixedSize(true)
                    layoutManager = LinearLayoutManager(activity)
                    adapter = TvShowAdapter(it) { tv ->
                        val intent = Intent(activity, DetailActivity::class.java)
                        intent.putExtra("tv_show", tv)
                        startActivity(intent)
                    }
                    pb_tv_show.visibility = View.GONE
                }
            } else {
                pb_tv_show.visibility = View.GONE
                tv_tv_show_empty.visibility = View.VISIBLE
            }
        })
    }
}
