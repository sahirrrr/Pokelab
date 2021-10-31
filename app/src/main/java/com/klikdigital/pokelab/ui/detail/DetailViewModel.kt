package com.klikdigital.pokelab.ui.detail

import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.ViewModel
import com.klikdigital.pokelab.domain.usecase.PokemonUseCase

class DetailViewModel(private val pokemonUseCase : PokemonUseCase) : ViewModel() {

    fun getDetailPokemon(id: String) = LiveDataReactiveStreams.fromPublisher(pokemonUseCase.getDetailPokemon(id))

    fun getPokemonSpecies(id: String) = LiveDataReactiveStreams.fromPublisher(pokemonUseCase.getPokemonSpecies(id))

    fun getEvolutionPokemon(id: String, url: String) = LiveDataReactiveStreams.fromPublisher(pokemonUseCase.getEvolutionPokemon(id, url))
}