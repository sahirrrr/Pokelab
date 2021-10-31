package com.klikdigital.pokelab.domain.repository

import com.klikdigital.pokelab.core.data.Resource
import com.klikdigital.pokelab.domain.model.DetailPokemonModel
import com.klikdigital.pokelab.domain.model.EvolutionPokemonModel
import com.klikdigital.pokelab.domain.model.PokemonListModel
import com.klikdigital.pokelab.domain.model.PokemonSpeciesModel
import io.reactivex.Flowable

interface IPokemonRepository {

    fun getPokemonList() : Flowable<Resource<List<PokemonListModel>>>

    fun getDetailPokemon(id: String) : Flowable<Resource<List<DetailPokemonModel>>>

    fun getPokemonSpecies(id: String) : Flowable<Resource<List<PokemonSpeciesModel>>>

    fun getEvolutionPokemon(id: String ,url: String) : Flowable<Resource<List<EvolutionPokemonModel>>>
}