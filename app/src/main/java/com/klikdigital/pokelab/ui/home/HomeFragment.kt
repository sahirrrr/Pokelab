package com.klikdigital.pokelab.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.klikdigital.pokelab.core.data.Resource
import com.klikdigital.pokelab.databinding.FragmentHomeBinding
import com.klikdigital.pokelab.ui.adapter.PokemonListAdapter
import org.koin.android.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding
    private var root : View? = null
    private val viewModel : HomeViewModel by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        root = binding?.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        pokemonList()
    }

    private fun pokemonList() {
      val pokemonListAdapter = PokemonListAdapter()
      viewModel.getPokemonList().observe(viewLifecycleOwner, { pokemonList ->
          if (pokemonList != null) {
              when(pokemonList) {
                  is Resource.Success -> {
                      binding?.progressBar?.visibility = View.GONE
                      val dataArray = pokemonList.data
                      pokemonListAdapter.setData(dataArray)
                      pokemonListAdapter.notifyDataSetChanged()
                  }
                  is Resource.Error -> {
                      binding?.progressBar?.visibility = View.GONE
                      Toast.makeText(requireContext(), "Error", Toast.LENGTH_SHORT).show()
                  }
                  is Resource.Loading -> binding?.progressBar?.visibility = View.VISIBLE
              }
          }
      })

        with(binding?.rvPokemon) {
            this?.layoutManager = GridLayoutManager(context, 2)
            this?.setHasFixedSize(true)
            this?.adapter = pokemonListAdapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        root = null
    }
}
