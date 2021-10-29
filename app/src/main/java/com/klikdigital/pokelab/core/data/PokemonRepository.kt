package com.klikdigital.pokelab.core.data

import com.klikdigital.pokelab.core.data.source.local.LocalDataSource
import com.klikdigital.pokelab.core.data.source.remote.RemoteDataSource
import com.klikdigital.pokelab.core.data.source.remote.network.ApiResponse
import com.klikdigital.pokelab.core.data.source.remote.response.PokemonListResponse
import com.klikdigital.pokelab.core.utils.DataMapper
import com.klikdigital.pokelab.domain.model.PokemonListModel
import com.klikdigital.pokelab.domain.repository.IPokemonRepository
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class PokemonRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : IPokemonRepository {
    override fun getPokemonList(): Flowable<Resource<List<PokemonListModel>>> =
        object : NetworkBoundResource<List<PokemonListModel>, List<PokemonListResponse>>() {
            override fun loadFromDB(): Flowable<List<PokemonListModel>> {
                return localDataSource.getPokemonList().map { DataMapper.mapPokemonListEntitiesToDomain(it) }
            }

            override fun shouldFetch(data: List<PokemonListModel>?): Boolean {
                return data == null || data.isEmpty()
            }

            override fun saveCallResult(data: List<PokemonListResponse>) {
                val pokemonList = DataMapper.mapPokemonListResponseToEntitites(data)
                localDataSource.insertPokemonList(pokemonList)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe()
            }

            override fun createCall(): Flowable<ApiResponse<List<PokemonListResponse>>> {
                return remoteDataSource.getPokemonList()
            }
        }.asFlowAble()
}