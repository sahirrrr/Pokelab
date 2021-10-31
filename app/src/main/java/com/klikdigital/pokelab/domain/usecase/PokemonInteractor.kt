package com.klikdigital.pokelab.domain.usecase

import com.klikdigital.pokelab.core.data.Resource
import com.klikdigital.pokelab.domain.model.DetailPokemonModel
import com.klikdigital.pokelab.domain.model.EvolutionPokemonModel
import com.klikdigital.pokelab.domain.model.PokemonListModel
import com.klikdigital.pokelab.domain.model.PokemonSpeciesModel
import com.klikdigital.pokelab.domain.repository.IPokemonRepository
import io.reactivex.Flowable

class PokemonInteractor(private val pokemonRepositoryImp : IPokemonRepository): PokemonUseCase {
    override fun getPokemonList(): Flowable<Resource<List<PokemonListModel>>> {
        return pokemonRepositoryImp.getPokemonList()
    }

    override fun getDetailPokemon(id: String): Flowable<Resource<List<DetailPokemonModel>>> {
        return pokemonRepositoryImp.getDetailPokemon(id)
    }

    override fun getPokemonSpecies(id: String): Flowable<Resource<List<PokemonSpeciesModel>>> {
        return pokemonRepositoryImp.getPokemonSpecies(id)
    }

    override fun getEvolutionPokemon(id: String, url: String): Flowable<Resource<List<EvolutionPokemonModel>>> {
        return pokemonRepositoryImp.getEvolutionPokemon(id, url)
    }
}