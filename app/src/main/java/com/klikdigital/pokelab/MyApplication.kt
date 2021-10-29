package com.klikdigital.pokelab

import android.app.Application
import com.klikdigital.pokelab.core.di.databaseModule
import com.klikdigital.pokelab.core.di.networkModule
import com.klikdigital.pokelab.core.di.pokemonModules
import com.klikdigital.pokelab.di.useCaseModule
import com.klikdigital.pokelab.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@MyApplication)
            modules(
                listOf(
                    databaseModule,
                    networkModule,
                    pokemonModules,
                    useCaseModule,
                    viewModelModule
                )
            )
        }
    }
}