package id.haadii.submission.dicoding.repoandlivedata.tvshow

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
import kotlinx.android.synthetic.main.fragment_tv_show.*

class TvShowFragment : Fragment() {

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tv_show, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        if (activity != null) {
            viewModel = obtainViewModel(activity!!)
        }

        viewModel.setTvShow().observe(this, androidx.lifecycle.Observer {
            rv_tv_show.apply {
                setHasFixedSize(true)
                layoutManager = LinearLayoutManager(activity)
                adapter = TvShowAdapter(it.results) { tv ->
                    val intent = Intent(activity, DetailActivity::class.java)
                    intent.putExtra("tv_show", tv)
                    startActivity(intent)
                }
                pb_tv_show.visibility = View.GONE
            }
        })
    }

    private fun obtainViewModel(activity: FragmentActivity) : MainViewModel {
        val factory = ViewModelFactory.getInstance(activity.application)
        return ViewModelProviders.of(activity, factory).get(MainViewModel::class.java)
    }

}
