package com.sambataro.ignacio.anwbassesment

import android.app.Application
import com.jakewharton.threetenabp.AndroidThreeTen
import com.sambataro.ignacio.anwbassesment.data.ApiCurrentUser
import com.sambataro.ignacio.anwbassesment.data.db.OwnerDataBase
import com.sambataro.ignacio.anwbassesment.data.network.ConnectivityInterceptor
import com.sambataro.ignacio.anwbassesment.data.network.ConnectivityInterceptorImpl
import com.sambataro.ignacio.anwbassesment.data.network.OwnerNetworkDataSource
import com.sambataro.ignacio.anwbassesment.data.network.OwnerNetworkDataSourceImpl
import com.sambataro.ignacio.anwbassesment.data.repository.OwnerRepository
import com.sambataro.ignacio.anwbassesment.data.repository.OwnerRepositoryImpl
import com.sambataro.ignacio.anwbassesment.internal.InfinumViewModelFactory
import com.sambataro.ignacio.anwbassesment.ui.infinum.InfinumFragment
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.androidModule
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class CurrentRepoUserApplication : Application(), KodeinAware {
    override val kodein = Kodein.lazy {
        import(androidXModule(this@CurrentRepoUserApplication))

        bind() from singleton { OwnerDataBase(instance())}
        bind() from singleton { instance<OwnerDataBase>().currentOwnerDAO()}
        bind<ConnectivityInterceptor>() with singleton { ConnectivityInterceptorImpl(instance())}
        bind() from singleton { ApiCurrentUser(instance())}
        bind<OwnerNetworkDataSource>() with singleton { OwnerNetworkDataSourceImpl(instance())}
        bind<OwnerRepository>() with singleton { OwnerRepositoryImpl(instance(), instance()) }
        bind() from provider { InfinumViewModelFactory(instance())}
    }

    override fun onCreate() {
        super.onCreate()
        AndroidThreeTen.init(this)
    }
}