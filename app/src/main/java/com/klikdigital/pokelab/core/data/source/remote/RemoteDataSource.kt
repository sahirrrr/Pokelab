package com.klikdigital.pokelab.core.data.source.remote

import android.annotation.SuppressLint
import android.util.Log
import com.klikdigital.pokelab.core.data.source.remote.network.ApiResponse
import com.klikdigital.pokelab.core.data.source.remote.network.ApiService
import com.klikdigital.pokelab.core.data.source.remote.response.DetailPokemonResponse
import com.klikdigital.pokelab.core.data.source.remote.response.EvolutionPokemonResponse
import com.klikdigital.pokelab.core.data.source.remote.response.PokemonListResponse
import com.klikdigital.pokelab.core.data.source.remote.response.PokemonSpeciesResponse
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject

@SuppressLint("CheckResult")
class RemoteDataSource(private val apiService: ApiService) {

    fun getPokemonList() : Flowable<ApiResponse<PokemonListResponse>> {
        val responseResult = PublishSubject.create<ApiResponse<PokemonListResponse>>()
        val client = apiService.getListPokemon()
        client
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .take(1)
            .subscribe ({ response ->
                responseResult.onNext(if (response.results.isNotEmpty()) ApiResponse.Success(response) else ApiResponse.Empty)
            },{ error ->
                responseResult.onNext(ApiResponse.Error(error.message.toString()))
                Log.e("RemoteDataSource, cause", error.toString())
            })
        return responseResult.toFlowable(BackpressureStrategy.BUFFER)
    }

    fun getDetailPokemon(id: String) : Flowable<ApiResponse<DetailPokemonResponse>> {
        val responseResult = PublishSubject.create<ApiResponse<DetailPokemonResponse>>()
        val client = apiService.getDetailPokemon(id)
        client
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .take(1)
            .subscribe ({ response ->
                responseResult.onNext(if (response != null) ApiResponse.Success(response) else ApiResponse.Empty)
            },{ error ->
                responseResult.onNext(ApiResponse.Error(error.message.toString()))
                Log.e("RemoteDataSource, cause", error.toString())
            })
        return responseResult.toFlowable(BackpressureStrategy.BUFFER)
    }

    fun getPokemonSpecies(id: String) : Flowable<ApiResponse<PokemonSpeciesResponse>> {
        val responseResult = PublishSubject.create<ApiResponse<PokemonSpeciesResponse>>()
        val client = apiService.getPokemonSpecies(id)
        client
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .take(1)
            .subscribe ({ response ->
                responseResult.onNext(if (response != null) ApiResponse.Success(response) else ApiResponse.Empty)
            },{ error ->
                responseResult.onNext(ApiResponse.Error(error.message.toString()))
                Log.e("RemoteDataSource, cause", error.toString())
            })
        return responseResult.toFlowable(BackpressureStrategy.BUFFER)
    }

    fun getEvolutionPokemon(url: String) : Flowable<ApiResponse<EvolutionPokemonResponse>> {
        val responseResult = PublishSubject.create<ApiResponse<EvolutionPokemonResponse>>()
        val client = apiService.getEvolutionPokemon(url)
        client
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .take(1)
            .subscribe ({ response ->
                responseResult.onNext(if (response != null) ApiResponse.Success(response) else ApiResponse.Empty)
            },{ error ->
                responseResult.onNext(ApiResponse.Error(error.message.toString()))
                Log.e("RemoteDataSource, cause", error.toString())
            })
        return responseResult.toFlowable(BackpressureStrategy.BUFFER)
    }
}