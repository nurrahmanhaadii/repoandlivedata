package id.haadii.submission.dicoding.repoandlivedata.utils

import android.app.Application
import androidx.annotation.NonNull
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import id.haadii.submission.dicoding.repoandlivedata.detail.DetailViewModel
import id.haadii.submission.dicoding.repoandlivedata.di.Injection
import id.haadii.submission.dicoding.repoandlivedata.main.MainViewModel
import id.haadii.submission.dicoding.repoandlivedata.repository.local.LocalRepository
import id.haadii.submission.dicoding.repoandlivedata.repository.network.NetworkRepository

class ViewModelFactory(
    private val repository: NetworkRepository,
    private val localRepository: LocalRepository,
    private val application: Application
) : ViewModelProvider.NewInstanceFactory() {

    companion object {
        private var INSTANCE: ViewModelFactory? = null
        fun getInstance(application: Application): ViewModelFactory? {
            if (INSTANCE == null) {
                synchronized(ViewModelFactory::class) {
                    if (INSTANCE == null) {
                        INSTANCE = ViewModelFactory(
                            Injection.provideRepository(),
                            Injection.provideLocalRepository(application),
                            application
                        )
                    }
                }
            }
            return INSTANCE
        }
    }

    @NonNull
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(repository, localRepository) as T
        } else if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
            return DetailViewModel(application, localRepository) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }
}