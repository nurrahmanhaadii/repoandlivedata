package id.haadii.submission.dicoding.repoandlivedata.movie


import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import id.haadii.submission.dicoding.repoandlivedata.R
import id.haadii.submission.dicoding.repoandlivedata.detail.DetailActivity
import id.haadii.submission.dicoding.repoandlivedata.main.MainViewModel
import id.haadii.submission.dicoding.repoandlivedata.utils.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_movie.*

/**
 * A simple [Fragment] subclass.
 */
class MovieFragment : Fragment() {

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        if (activity != null) {
            viewModel = obtainViewModel(activity!!)
        }

        viewModel.setMovie().observe(this, androidx.lifecycle.Observer {
            if (it.results.isNotEmpty()) {
                tv_movie_empty.visibility = View.GONE
                rv_movie.apply {
                    setHasFixedSize(true)
                    layoutManager = LinearLayoutManager(activity)
                    adapter = MovieAdapter(it.results) { movie ->
                        val intent = Intent(activity, DetailActivity::class.java)
                        intent.putExtra("movie", movie)
                        startActivity(intent)
                    }
                    pb_movie.visibility = View.GONE
                }
            } else {
                tv_movie_empty.visibility = View.VISIBLE
                pb_movie.visibility = View.GONE
            }

        })
    }

    private fun obtainViewModel(activity: FragmentActivity): MainViewModel {
        val factory = ViewModelFactory.getInstance(activity.application)
        return ViewModelProviders.of(activity, factory).get(MainViewModel::class.java)
    }

}