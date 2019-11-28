package id.haadii.submission.dicoding.repoandlivedata.di

import android.app.Application
import id.haadii.submission.dicoding.repoandlivedata.repository.local.LocalRepository
import id.haadii.submission.dicoding.repoandlivedata.repository.network.NetworkRepository

class Injection {
    companion object {
        fun provideRepository() : NetworkRepository {
            return NetworkRepository()
        }
        fun provideLocalRepository(application: Application) : LocalRepository {
            return LocalRepository(application)
        }
    }
}