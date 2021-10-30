package com.klikdigital.pokelab.domain.repository

import com.klikdigital.pokelab.core.data.Resource
import com.klikdigital.pokelab.domain.model.DetailPokemonModel
import com.klikdigital.pokelab.domain.model.PokemonListModel
import io.reactivex.Flowable

interface IPokemonRepository {

    fun getPokemonList() : Flowable<Resource<List<PokemonListModel>>>

    fun getDetailPokemon(id: String) : Flowable<Resource<DetailPokemonModel>>
}