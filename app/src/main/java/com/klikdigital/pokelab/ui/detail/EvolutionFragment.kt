package com.klikdigital.pokelab.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.klikdigital.pokelab.BuildConfig
import com.klikdigital.pokelab.R
import com.klikdigital.pokelab.core.data.Resource
import com.klikdigital.pokelab.core.utils.Helper
import com.klikdigital.pokelab.databinding.FragmentEvolutionBinding
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.launch
import org.koin.android.viewmodel.ext.android.viewModel

@DelicateCoroutinesApi
class EvolutionFragment : Fragment() {

    private var _binding: FragmentEvolutionBinding? = null
    private val binding get() = _binding
    private var root: View? = null

    private val viewModel: DetailViewModel by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentEvolutionBinding.inflate(inflater, container, false)
        root = binding?.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // get Activity progress bar for fragment
        val progressBar = activity?.findViewById<ProgressBar>(R.id.progress_bar)

        // get id from Detail Activity
        val id = requireActivity().intent.getStringExtra(DetailActivity.EXTRA_ID)
        if (id != null) {
            pokemonSpecies(progressBar, id)
        }
    }

    private fun pokemonSpecies(progressBar: ProgressBar?, id : String) {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.getPokemonSpecies(id).observe(viewLifecycleOwner, { pokemonSpecies ->
                if (pokemonSpecies != null) {
                    when (pokemonSpecies) {
                        is Resource.Success -> {
                            val speciesData = pokemonSpecies.data!!
                            for (data in speciesData.indices) {
                                progressBar?.visibility = View.GONE

                                val url = speciesData[data].evolutionChain.url
                                val newUrl = Helper.getEvolutionURLFromSpecies(url)
                                val idSpecies = Helper.getSpeciesIDFromURLEvolution(url)

                                pokemonEvolution(progressBar, idSpecies!! ,newUrl!!)
                            }
                        }
                        is Resource.Error -> {
                            progressBar?.visibility = View.GONE
                            binding?.icForward1?.visibility = View.GONE
                            binding?.icForward2?.visibility = View.GONE
                            binding?.viewLine?.visibility = View.GONE
                            binding?.ivEmptyState?.visibility = View.VISIBLE
                            binding?.tvEmptyStateTitle?.visibility = View.VISIBLE
                            binding?.tvEmptyStateDecs?.visibility = View.VISIBLE
                        }
                        is Resource.Loading -> progressBar?.visibility = View.VISIBLE
                    }
                }
            })
        }
    }

    private fun pokemonEvolution(progressBar: ProgressBar?, id: String, url: String) {
        viewModel.getEvolutionPokemon(id, url).observe(viewLifecycleOwner, { evolutionPokemon ->
            if (evolutionPokemon != null) {
                when (evolutionPokemon) {
                    is Resource.Success -> {
                        val dataEvolution = evolutionPokemon.data!!
                        val dataLevel = dataEvolution[0].chain.evolvesTo
                        progressBar?.visibility = View.GONE

                        // Pokemon Base
                        val dataEvo = dataEvolution[0].chain.species
                        val idEvoBase = Helper.getImageIDFromURLEvolution(dataEvo.url)

                        binding?.tvPokemonName1?.text = dataEvo.name
                        binding?.ivPokemon1?.let {
                            Glide.with(this)
                                .load("${BuildConfig.IMAGE_URL + idEvoBase}.png")
                                .into(it)
                        }

                        // Pokemon First Evolution
                        val dataLevelEvo = dataLevel[0].evolutionDetails[0]
                        val dataLvl = dataLevel[0]
                        val idFirstEvo = Helper.getImageIDFromURLEvolution(dataLvl.species.url)

                        binding?.tvLevel1?.text = Helper.lvlConverter(dataLevelEvo.minLevel)
                        binding?.tvPokemonName2?.text = dataLvl.species.name
                        binding?.ivPokemon2?.let {
                            Glide.with(this)
                                .load("${BuildConfig.IMAGE_URL + idFirstEvo}.png")
                                .into(it)
                        }
                        binding?.tvPokemonName3?.text = dataLvl.species.name
                        binding?.ivPokemon3?.let {
                            Glide.with(this)
                                .load("${BuildConfig.IMAGE_URL + idFirstEvo}.png")
                                .into(it)
                        }

                        // Pokemon Second Evolution
                        val dataEvoDetailSize = dataLevel[0].evolvesTo.size
                        if (dataEvoDetailSize != 0) {
                            val dataEvoDetail = dataLevel[0].evolvesTo[0].evolutionDetails[0]
                            val dataEvolvesTo = dataLevel[0].evolvesTo[0].species
                            val idSecondEvo = Helper.getImageIDFromURLEvolution(dataEvolvesTo.url)

                            binding?.tvLevel2?.text = Helper.lvlConverter(dataEvoDetail.minLevel)
                            binding?.tvPokemonName4?.text = dataEvolvesTo.name
                            binding?.ivPokemon4?.let {
                                Glide.with(this)
                                    .load("${BuildConfig.IMAGE_URL + idSecondEvo}.png")
                                    .into(it)
                            }
                        } else {
                            binding?.viewLine?.visibility = View.GONE
                            binding?.tvLevel2?.visibility = View.GONE
                            binding?.icForward2?.visibility = View.GONE
                            binding?.ivPokemon3?.visibility = View.GONE
                            binding?.tvPokemonName3?.visibility = View.GONE
                            binding?.tvPokemonName4?.visibility = View.GONE
                        }
                    }
                    is Resource.Error -> {
                        progressBar?.visibility = View.GONE
                        binding?.ivEmptyState?.visibility = View.VISIBLE
                        binding?.tvEmptyStateTitle?.visibility = View.VISIBLE
                        binding?.tvEmptyStateDecs?.visibility = View.VISIBLE
                    }
                    is Resource.Loading -> progressBar?.visibility = View.VISIBLE
                }
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        root = null
    }
}