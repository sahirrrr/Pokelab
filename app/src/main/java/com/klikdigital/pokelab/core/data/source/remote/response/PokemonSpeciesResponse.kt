package com.klikdigital.pokelab.core.data.source.remote.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class PokemonSpeciesResponse(

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("evolution_chain")
	val evolutionChain: EvolutionChain,
)

@Parcelize
data class EvolutionChain(

	@field:SerializedName("url")
	val url: String
): Parcelable
