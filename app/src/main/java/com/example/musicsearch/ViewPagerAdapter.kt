package com.example.musicsearch

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.musicsearch.fragments.ClassicFragment
import com.example.musicsearch.fragments.PopFragment
import com.example.musicsearch.fragments.RockFragment

private const val NUM_TABS = 3

public class ViewPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle):
FragmentStateAdapter(fragmentManager, lifecycle){

    override fun getItemCount(): Int {
        return NUM_TABS
    }

    override fun createFragment(position: Int): Fragment {
        when (position){
            0 -> return RockFragment()
            1 -> return ClassicFragment()
        }
        return PopFragment()
    }
}