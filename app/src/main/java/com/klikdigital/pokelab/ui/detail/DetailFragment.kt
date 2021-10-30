package com.klikdigital.pokelab.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.klikdigital.pokelab.R
import com.klikdigital.pokelab.core.data.Resource
import com.klikdigital.pokelab.databinding.FragmentDetailBinding
import com.klikdigital.pokelab.ui.adapter.PokemonTypeAdapter
import com.klikdigital.pokelab.ui.adapter.SectionsPagerAdapter
import org.koin.android.viewmodel.ext.android.viewModel

class DetailFragment : Fragment() {

    companion object {
        @StringRes
        private val TAB_TITLES = intArrayOf(
            R.string.tab_text_1,
            R.string.tab_text_2
        )
    }

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding
    private var root : View? = null
    private val viewModel : DetailViewModel by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        root = binding?.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sectionsPagerAdapter = SectionsPagerAdapter(this)
        val viewPager: ViewPager2? = binding?.viewPager
        viewPager?.adapter = sectionsPagerAdapter

        val tabs: TabLayout? = binding?.tabs
        if (tabs != null && viewPager != null) {
            TabLayoutMediator(tabs, viewPager) { tab, position ->
                tab.text = resources.getString(TAB_TITLES[position])
            }.attach()
        }

        pokemonDetail("4")
    }

    private fun pokemonDetail(id: String) {
        val pokemonTypeAdapter = PokemonTypeAdapter()
        viewModel.getDetailPokemon(id).observe(viewLifecycleOwner, { detailPokemon ->
            if (detailPokemon != null) {
                when(detailPokemon) {
                    is Resource.Success -> {
                        binding?.progressBar?.visibility = View.GONE
                        val dataArray = detailPokemon.data?.types
                        pokemonTypeAdapter.setData(dataArray)
                        pokemonTypeAdapter.notifyDataSetChanged()

                        binding?.tvPokemonName?.text = detailPokemon.data?.name
                        binding?.tvPokemonHeight?.text = detailPokemon.data?.height.toString()
                        binding?.tvPokemonWeight?.text = detailPokemon.data?.weight.toString()

                        binding?.ivPokemon?.let {
                            Glide.with(this)
                                .load(detailPokemon.data?.sprites?.pokeImage)
                                .into(it)
                        }
                    }
                    is Resource.Error -> {
                        binding?.progressBar?.visibility = View.GONE
                        Toast.makeText(requireContext(), "Error", Toast.LENGTH_SHORT).show()
                    }
                    is Resource.Loading -> binding?.progressBar?.visibility = View.VISIBLE
                }
            }
        })

        with(binding?.rvTypePokemon) {
            this?.layoutManager = LinearLayoutManager(context)
            this?.setHasFixedSize(true)
            this?.adapter = pokemonTypeAdapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        root = null
    }
}