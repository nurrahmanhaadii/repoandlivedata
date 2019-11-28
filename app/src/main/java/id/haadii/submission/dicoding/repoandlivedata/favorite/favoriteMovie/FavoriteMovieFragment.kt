package id.haadii.submission.dicoding.repoandlivedata.favorite.favoriteMovie


import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import androidx.paging.PagedList
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import id.haadii.submission.dicoding.repoandlivedata.R
import id.haadii.submission.dicoding.repoandlivedata.main.MainViewModel
import java.util.*

import id.haadii.submission.dicoding.repoandlivedata.detail.DetailActivity
import id.haadii.submission.dicoding.repoandlivedata.movie.MovieAdapter
import id.haadii.submission.dicoding.repoandlivedata.movie.MoviePagedListAdapter
import id.haadii.submission.dicoding.repoandlivedata.repository.local.entity.MovieEntity
import kotlinx.android.synthetic.main.fragment_movie.*
import kotlinx.android.synthetic.main.fragment_movie.pb_movie

class FavoriteMovieFragment : Fragment() {

    private lateinit var viewModel: MainViewModel
    private lateinit var moviePagedListAdapter: MoviePagedListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movie, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(Objects.requireNonNull(activity!!)).get(MainViewModel::class.java)

        tv_movie_empty.visibility = View.VISIBLE

        initObserver()

    }

    private fun initObserver() {
        viewModel.getMovieList().observe(this, androidx.lifecycle.Observer {
            if (it != null) {
                tv_movie_empty.visibility = View.GONE
                moviePagedListAdapter = MoviePagedListAdapter { movie ->
                    val intent = Intent(
                        activity,
                        DetailActivity::class.java
                    )
                    intent.putExtra("movie", movie)
                    startActivity(intent)
                }

                moviePagedListAdapter.submitList(it)
                rv_movie.layoutManager = LinearLayoutManager(activity)
                rv_movie.setHasFixedSize(true)
                rv_movie.adapter = moviePagedListAdapter
                pb_movie.visibility = View.GONE

            } else {
                pb_movie.visibility = View.GONE
                tv_movie_empty.visibility = View.VISIBLE
            }
        })
    }

}
