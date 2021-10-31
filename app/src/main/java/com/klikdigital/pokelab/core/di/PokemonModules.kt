package com.klikdigital.pokelab.core.di

import androidx.room.Room
import com.klikdigital.pokelab.BuildConfig
import com.klikdigital.pokelab.core.data.PokemonRepository
import com.klikdigital.pokelab.core.data.source.local.LocalDataSource
import com.klikdigital.pokelab.core.data.source.local.room.PokemonDB
import com.klikdigital.pokelab.core.data.source.remote.RemoteDataSource
import com.klikdigital.pokelab.core.data.source.remote.network.ApiService
import com.klikdigital.pokelab.domain.repository.IPokemonRepository
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val databaseModule = module {
    factory { get<PokemonDB>().pokemonDao() }
    single {
        Room.databaseBuilder(
            androidContext(),
            PokemonDB::class.java, "pokemon_db"
        ).fallbackToDestructiveMigration().build()
    }
}

val networkModule = module {
    single {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .build()
    }
    single {
        val retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(get())
            .build()
        retrofit.create(ApiService::class.java)
    }
}

val pokemonModules = module {
    single { LocalDataSource(get()) }
    single { RemoteDataSource(get()) }
    single<IPokemonRepository> { PokemonRepository(get(), get()) }
}