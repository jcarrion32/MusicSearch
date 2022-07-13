package com.example.musicsearch

import android.graphics.Color.blue
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import com.example.musicsearch.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

val tabArray = arrayOf(
    "Rock",
    "Classic",
    "Pop"
)

val iconArray = arrayOf(
    R.mipmap.ic_guitar_foreground,
    R.mipmap.ic_harp_foreground,
    R.mipmap.ic_star_foreground

)

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        val vpFragments = binding.vpFragments
        val tabLayout = binding.tabLayout

        val adapter = ViewPagerAdapter(supportFragmentManager, lifecycle)
        vpFragments.adapter = adapter

        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            @RequiresApi(Build.VERSION_CODES.M)
            override fun onTabSelected(tab: TabLayout.Tab?) {
                when (tab?.position) {
                    0 -> updateTabText(0)
                    1 -> updateTabText(1)
                    2 -> updateTabText(2)
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {}
            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })


        TabLayoutMediator(tabLayout, vpFragments) { tab, position ->
            tab.setIcon(iconArray[position])
            tab.text = tabArray[position]
        }.attach()

    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun updateTabText(platform: Int) {
        when (platform) {
            0 -> {
                binding.tabLayout.apply {
                    setTabTextColors(
                        resources.getColor(R.color.grey, null),
                        resources.getColor(R.color.red, null)
                    )
                    getTabAt(platform)?.icon?.setTint(
                        resources.getColor(R.color.red, null)
                    )
                }
            }
            1 -> {
                binding.tabLayout.apply {
                    setTabTextColors(
                        resources.getColor(R.color.grey, null),
                        resources.getColor(R.color.red, null)
                    )
                    getTabAt(platform)?.icon?.setTint(
                        resources.getColor(R.color.red, null)
                    )
                }
            }
            2 -> {
                binding.tabLayout.apply {
                    setTabTextColors(
                        resources.getColor(R.color.grey, null),
                        resources.getColor(R.color.red, null)
                    )
                    getTabAt(platform)?.icon?.setTint(
                        resources.getColor(R.color.red, null)
                    )
                }
            }
        }
    }
}