package id.haadii.submission.dicoding.repoandlivedata.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import id.haadii.submission.dicoding.repoandlivedata.R
import id.haadii.submission.dicoding.repoandlivedata.favorite.FavoriteFragment
import id.haadii.submission.dicoding.repoandlivedata.movie.MovieFragment
import id.haadii.submission.dicoding.repoandlivedata.tvshow.TvShowFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupBottomNavigation()
    }

    private fun setupBottomNavigation() {
        val bottomNavigation: BottomNavigationView = findViewById(R.id.bottom_nav)
        bottomNavigation.setOnNavigationItemSelectedListener(navigationItemSelectedListener())
        bottomNavigation.selectedItemId = R.id.movie
    }

    private fun navigationItemSelectedListener() : BottomNavigationView.OnNavigationItemSelectedListener {
        return BottomNavigationView.OnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.movie -> {
                    val fragment = MovieFragment()
                    openFragment(fragment)
                    title = resources.getString(R.string.movie)
                    return@OnNavigationItemSelectedListener true
                }
                R.id.tv_show -> {
                    val fragment = TvShowFragment()
                    openFragment(fragment)
                    title = resources.getString(R.string.tv_show)
                    return@OnNavigationItemSelectedListener true
                }
                R.id.favorite -> {
                    val fragment = FavoriteFragment()
                    openFragment(fragment)
                    title = resources.getString(R.string.favorite)
                    return@OnNavigationItemSelectedListener true
                }
            }
            false
        }
    }

    private fun openFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.contentFrame, fragment)
        transaction.commit()
    }
}
