package com.klikdigital.pokelab.ui.detail

import android.os.Bundle
import android.os.Handler
import android.os.Looper
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
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.launch
import org.koin.android.viewmodel.ext.android.viewModel

@DelicateCoroutinesApi
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

        // get id from Detail Activity
        val id = requireActivity().intent.getStringExtra(DetailActivity.EXTRA_ID)
        if (id != null) {
            pokemonDetail(id)
        }
    }

    private fun pokemonDetail(id: String) {
        // get Activity progress bar for Fragment
        val progressBar = activity?.findViewById<ProgressBar>(R.id.progress_bar)
        Handler(Looper.getMainLooper()).postDelayed({
            viewModel.getDetailPokemon(id).observe(viewLifecycleOwner, { detailPokemon ->
                if (detailPokemon != null) {
                    when (detailPokemon) {
                        is Resource.Success -> {
                            val dataDetail = detailPokemon.data!!
                            val dataStats = dataDetail[0].stats
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
                        is Resource.Error -> {
                            progressBar?.visibility = View.GONE
                            binding?.tvHp?.visibility = View.GONE
                            binding?.tvAttack?.visibility = View.GONE
                            binding?.tvDefense?.visibility = View.GONE
                            binding?.tvSpecialAttack?.visibility = View.GONE
                            binding?.tvSpecialDefense?.visibility = View.GONE
                            binding?.tvSpeed?.visibility = View.GONE
                            binding?.ivEmptyState?.visibility = View.VISIBLE
                            binding?.tvEmptyStateTitle?.visibility = View.VISIBLE
                            binding?.tvEmptyStateDecs?.visibility = View.VISIBLE
                        }
                        is Resource.Loading -> progressBar?.visibility = View.VISIBLE
                    }
                }
            })
        }, 200)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        root = null
    }
}