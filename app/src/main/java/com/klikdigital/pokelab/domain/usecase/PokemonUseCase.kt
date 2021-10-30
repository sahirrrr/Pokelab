package com.klikdigital.pokelab.domain.usecase

import com.klikdigital.pokelab.core.data.Resource
import com.klikdigital.pokelab.domain.model.DetailPokemonModel
import com.klikdigital.pokelab.domain.model.PokemonListModel
import io.reactivex.Flowable

interface PokemonUseCase {

    fun getPokemonList() : Flowable<Resource<List<PokemonListModel>>>

    fun getDetailPokemon(id: String) : Flowable<Resource<DetailPokemonModel>>
}