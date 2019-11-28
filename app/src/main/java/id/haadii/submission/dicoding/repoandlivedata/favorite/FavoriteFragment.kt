package id.haadii.submission.dicoding.repoandlivedata.favorite


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProviders

import id.haadii.submission.dicoding.repoandlivedata.R
import id.haadii.submission.dicoding.repoandlivedata.main.MainViewModel
import id.haadii.submission.dicoding.repoandlivedata.main.MyPagerAdapter
import id.haadii.submission.dicoding.repoandlivedata.utils.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_favorite.*
import java.util.*

/**
 * A simple [Fragment] subclass.
 */
class FavoriteFragment : Fragment() {

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = obtainViewModel(activity!!)
        viewModel.getFavoriteMovie().observe(this, androidx.lifecycle.Observer {
            viewModel.setFavoriteMovie(it)
        })
        viewModel.getFavoriteTvShow().observe(this, androidx.lifecycle.Observer {
            viewModel.setFavoriteTvShow(it)
        })

        view_pager.adapter = MyPagerAdapter(childFragmentManager, activity!!)
        tab.setupWithViewPager(view_pager)
    }

    private fun obtainViewModel(activity: FragmentActivity) : MainViewModel {
        val factory = ViewModelFactory.getInstance(activity.application)
        return ViewModelProviders.of(Objects.requireNonNull(activity), factory).get(MainViewModel::class.java)
    }

}
