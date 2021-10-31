package com.klikdigital.pokelab.domain.model

import android.os.Parcelable
import com.klikdigital.pokelab.core.data.source.remote.response.Chain
import kotlinx.parcelize.Parcelize

@Parcelize
data class EvolutionPokemonModel(
    val id: Int,

    val chain: Chain,
): Parcelable