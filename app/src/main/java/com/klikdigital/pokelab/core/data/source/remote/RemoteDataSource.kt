package com.klikdigital.pokelab.core.data.source.remote

import android.annotation.SuppressLint
import android.util.Log
import com.klikdigital.pokelab.core.data.source.remote.network.ApiResponse
import com.klikdigital.pokelab.core.data.source.remote.network.ApiService
import com.klikdigital.pokelab.core.data.source.remote.response.PokemonListResponse
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject

@SuppressLint("CheckResult")
class RemoteDataSource(private val apiService: ApiService) {

    fun getPokemonList() : Flowable<ApiResponse<List<PokemonListResponse>>> {
        val responseResult = PublishSubject.create<ApiResponse<List<PokemonListResponse>>>()
        val client = apiService.getListPokemon()
        client
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .take(1)
            .subscribe ({ response ->
                responseResult.onNext(if (response.isNotEmpty()) ApiResponse.Success(response) else ApiResponse.Empty)
            },{ error ->
                responseResult.onNext(ApiResponse.Error(error.message.toString()))
                Log.e("RemoteDataSource, cause", error.toString())
            })
        return responseResult.toFlowable(BackpressureStrategy.BUFFER)
    }
}