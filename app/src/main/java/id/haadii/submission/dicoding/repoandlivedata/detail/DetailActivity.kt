package id.haadii.submission.dicoding.repoandlivedata.detail

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import id.haadii.submission.dicoding.repoandlivedata.R
import id.haadii.submission.dicoding.repoandlivedata.databinding.ActivityDetailBinding
import id.haadii.submission.dicoding.repoandlivedata.movie.DataItemMovie
import id.haadii.submission.dicoding.repoandlivedata.tvshow.DataItemTv
import id.haadii.submission.dicoding.repoandlivedata.utils.ViewModelFactory

class DetailActivity : AppCompatActivity() {

    private lateinit var viewModel: DetailViewModel
    private lateinit var detailActBinding: ActivityDetailBinding

    private var movie: DataItemMovie? = null
    private var tv: DataItemTv? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        detailActBinding = DataBindingUtil.setContentView(this, R.layout.activity_detail)
        viewModel = obtainViewModel()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        movie = intent.getParcelableExtra("movie")
        tv = intent.getParcelableExtra("tv_show")

        if (movie != null) {
            viewModel.setMovie(movie!!)
            viewModel.setDetailMovie()
            viewModel.setMovieEntity(movie!!)
            title = "Detail ${movie!!.title}"

            checkFavoriteMovie()
        } else if (tv != null) {
            viewModel.setTvShow(tv!!)
            viewModel.setDetailTvShow()
            viewModel.setTvShowEntity(tv!!)
            title = "Detail ${tv!!.name}"

            checkFavoriteTvShow()
        }

        detailActBinding.viewmodel = viewModel

    }

    private fun checkFavoriteMovie() {
        viewModel.favoriteMovies.observe(this, Observer {
            viewModel.checkFavoriteMovie(movie!!, it)
        })
    }

    private fun checkFavoriteTvShow() {
        viewModel.favoriteTvShows.observe(this, Observer {
            viewModel.checkFavoriteTv(tv!!, it)
        })
    }

    private fun obtainViewModel() : DetailViewModel {
        val factory = ViewModelFactory.getInstance(application)
        return ViewModelProviders.of(this, factory).get(DetailViewModel::class.java)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_favorite, menu)

        if (viewModel.isFavorite) {
            menu?.findItem(R.id.menu_favorite)?.icon = resources.getDrawable(R.drawable.ic_favorite)
        } else {
            menu?.findItem(R.id.menu_favorite)?.icon = resources.getDrawable(R.drawable.ic_unfavorite)
        }

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_favorite -> {
                if (movie != null) {
                    if (viewModel.isFavorite) {
                        viewModel.setUnfavoriteMovie(viewModel.getMovieEntity())
                        viewModel.isFavorite = false
                        item.icon = resources.getDrawable(R.drawable.ic_unfavorite)
                    } else {
                        viewModel.setFavoriteMovie(viewModel.getMovieEntity())
                        viewModel.isFavorite = true
                        item.icon = resources.getDrawable(R.drawable.ic_favorite)
                    }
                } else if (tv != null) {
                    if (viewModel.isFavorite) {
                        viewModel.setUnFavoriteTvShow(viewModel.getTvShowEntity())
                        viewModel.isFavorite = false
                        item.icon = resources.getDrawable(R.drawable.ic_unfavorite)
                    } else {
                        viewModel.setFavoriteTvShow(viewModel.getTvShowEntity())
                        viewModel.isFavorite = true
                        item.icon = resources.getDrawable(R.drawable.ic_favorite)
                    }
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}
