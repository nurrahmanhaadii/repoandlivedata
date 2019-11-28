package id.haadii.submission.dicoding.repoandlivedata.main

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import id.haadii.submission.dicoding.repoandlivedata.R
import id.haadii.submission.dicoding.repoandlivedata.favorite.favoriteMovie.FavoriteMovieFragment
import id.haadii.submission.dicoding.repoandlivedata.favorite.favoriteTvShow.FavoriteTvShowFragment
import id.haadii.submission.dicoding.repoandlivedata.movie.MovieFragment
import id.haadii.submission.dicoding.repoandlivedata.tvshow.TvShowFragment

class MyPagerAdapter(fragmentManager: FragmentManager, private val context: Context) : FragmentPagerAdapter(fragmentManager) {

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> {
                FavoriteMovieFragment()
            }
            else -> {
                return FavoriteTvShowFragment()
            }
        }
    }

    override fun getCount(): Int {
        return 2
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> {
                context.getString(R.string.movie)
            }
            else -> {
                context.getString(R.string.tv_show)
            }
        }
    }

}