package com.klikdigital.pokelab.core.data.source.remote.network

import com.klikdigital.pokelab.core.data.source.remote.response.DetailPokemonResponse
import com.klikdigital.pokelab.core.data.source.remote.response.EvolutionPokemonResponse
import com.klikdigital.pokelab.core.data.source.remote.response.PokemonListResponse
import com.klikdigital.pokelab.core.data.source.remote.response.PokemonSpeciesResponse
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("/api/v2/pokemon")
    fun getListPokemon() : Flowable<PokemonListResponse>

    @GET("/api/v2/pokemon/{id}")
    fun getDetailPokemon(@Path("id") id: String) : Flowable<DetailPokemonResponse>

    @GET("/api/v2/pokemon-species/{id}")
    fun getPokemonSpecies(@Path("id") id: String) : Flowable<PokemonSpeciesResponse>

    @GET("{url}")
    fun getEvolutionPokemon(@Path("url") url: String) : Flowable<EvolutionPokemonResponse>
}