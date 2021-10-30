package com.klikdigital.pokelab.core.data.source.remote.network

import com.klikdigital.pokelab.core.data.source.remote.response.DetailPokemonResponse
import com.klikdigital.pokelab.core.data.source.remote.response.PokemonListResponse
import io.reactivex.Flowable
import retrofit2.http.GET

interface ApiService {
    @GET("/api/v2/pokemon")
    fun getListPokemon() : Flowable<PokemonListResponse>

    @GET("api/v2/pokemon/{id}")
    fun getDetailPokemon(id: String) : Flowable<DetailPokemonResponse>
}