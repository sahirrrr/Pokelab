package com.klikdigital.pokelab.domain.model

import android.os.Parcelable
import com.klikdigital.pokelab.core.data.source.remote.response.EvolutionChain
import kotlinx.parcelize.Parcelize

@Parcelize
data class PokemonSpeciesModel(
    val id: Int,

    val evolutionChain: EvolutionChain,
): Parcelable