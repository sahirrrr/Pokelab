package com.klikdigital.pokelab.ui.home

import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.ViewModel
import com.klikdigital.pokelab.domain.usecase.PokemonUseCase

class HomeViewModel(private val pokemonUseCase : PokemonUseCase) : ViewModel() {

    fun getPokemonList() = LiveDataReactiveStreams.fromPublisher(pokemonUseCase.getPokemonList())
}