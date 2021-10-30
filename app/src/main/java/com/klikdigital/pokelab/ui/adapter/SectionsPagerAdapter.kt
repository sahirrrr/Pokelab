package com.klikdigital.pokelab.ui.adapter

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.klikdigital.pokelab.ui.detail.EvolutionFragment
import com.klikdigital.pokelab.ui.detail.StatsFragment

class SectionsPagerAdapter(activity: AppCompatActivity) : FragmentStateAdapter(activity) {
    override fun getItemCount(): Int =  2

    override fun createFragment(position: Int): Fragment {
        var fragment: Fragment? = null
        when (position) {
            0 -> fragment = StatsFragment()
            1 -> fragment = EvolutionFragment()
        }
        return fragment as Fragment
    }
}