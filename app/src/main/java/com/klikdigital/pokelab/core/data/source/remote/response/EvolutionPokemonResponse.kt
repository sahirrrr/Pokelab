package com.klikdigital.pokelab.core.data.source.remote.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class EvolutionPokemonResponse(

	@field:SerializedName("chain")
	val chain: Chain,

	@field:SerializedName("id")
	val id: Int,
)

@Parcelize
data class Chain(

	@field:SerializedName("species")
	val species: Species,

	@field:SerializedName("evolves_to")
	val evolvesTo: List<EvolvesToItem>,
): Parcelable

@Parcelize
data class EvolvesToItem(

	@field:SerializedName("evolution_details")
	val evolutionDetails: List<EvolutionDetailsItem>,

	@field:SerializedName("evolves_to")
	val evolvesTo: List<EvolvesToItem>,

	@field:SerializedName("species")
	val species: Species,
): Parcelable

@Parcelize
data class EvolutionDetailsItem(

	@field:SerializedName("min_level")
	val minLevel: Int
): Parcelable

@Parcelize
data class Species(

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("url")
	val url: String
): Parcelable
