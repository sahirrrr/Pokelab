package com.klikdigital.pokelab.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class PokemonEvolutionResponse(

	@field:SerializedName("chain")
	val chain: Chain,
)

data class Chain(

	@field:SerializedName("species")
	val species: Species,

	@field:SerializedName("evolves_to")
	val evolvesTo: List<EvolvesToItem>,
)

data class EvolvesToItem(

	@field:SerializedName("evolution_details")
	val evolutionDetails: List<EvolutionDetailsItem>,

	@field:SerializedName("species")
	val species: Species,


)

data class EvolutionDetailsItem(

	@field:SerializedName("min_level")
	val minLevel: Int
)

data class Species(

	@field:SerializedName("name")
	val name: String,
)
