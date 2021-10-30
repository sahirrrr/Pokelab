package com.klikdigital.pokelab.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.klikdigital.pokelab.R
import com.klikdigital.pokelab.core.data.Resource
import com.klikdigital.pokelab.databinding.FragmentStatsBinding
import kotlinx.coroutines.launch
import org.koin.android.viewmodel.ext.android.viewModel

class StatsFragment : Fragment() {

    private var _binding: FragmentStatsBinding? = null
    private val binding get() = _binding
    private var root: View? = null
    private val viewModel: DetailViewModel by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentStatsBinding.inflate(inflater, container, false)
        root = binding?.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val id = requireActivity().intent.getStringExtra(DetailActivity.EXTRA_ID)
        if (id != null) {
            pokemonDetail(id)
        }
    }

    private fun pokemonDetail(id: String) {
        // get Activity progress bar for fragment
        val progressBar = activity?.findViewById<ProgressBar>(R.id.progress_bar)

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.getDetailPokemon(id).observe(viewLifecycleOwner, { detailPokemon ->
                if (detailPokemon != null) {
                    when (detailPokemon) {
                        is Resource.Success -> {
                            val dataDetail = detailPokemon.data!!
                            for (data in dataDetail.indices) {
                                val dataStats = dataDetail[data].stats
                                for (stats in dataStats.indices) {
                                    progressBar?.visibility = View.GONE
                                    // Hp
                                    binding?.tvPokemonHp?.text = dataStats[0].baseStat.toString()
                                    binding?.progressHp?.progress = dataStats[0].baseStat

                                    // Attack
                                    binding?.tvPokemonAttack?.text = dataStats[1].baseStat.toString()
                                    binding?.progressAttack?.progress = dataStats[1].baseStat

                                    // Defense
                                    binding?.tvPokemonDefense?.text = dataStats[2].baseStat.toString()
                                    binding?.progressDefense?.progress = dataStats[2].baseStat

                                    // Special Attack
                                    binding?.tvPokemonSpecialAttack?.text = dataStats[3].baseStat.toString()
                                    binding?.progressSpecialAttack?.progress = dataStats[3].baseStat

                                    // Special Defense
                                    binding?.tvPokemonSpecialDefense?.text = dataStats[4].baseStat.toString()
                                    binding?.progressSpecialDefense?.progress = dataStats[4].baseStat

                                    // Speed
                                    binding?.tvPokemonSpeed?.text = dataStats[5].baseStat.toString()
                                    binding?.progressSpeed?.progress = dataStats[5].baseStat
                                }
                            }
                        }
                        is Resource.Error -> {
                            progressBar?.visibility = View.GONE
                            Toast.makeText(requireContext(), "Error", Toast.LENGTH_SHORT).show()
                        }
                        is Resource.Loading -> progressBar?.visibility = View.VISIBLE
                    }
                }
            })
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        root = null
    }

}