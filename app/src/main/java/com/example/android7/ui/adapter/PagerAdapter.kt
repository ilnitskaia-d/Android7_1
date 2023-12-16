package com.example.android7.ui.adapter

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.android7.ui.FragmentCameras
import com.example.android7.ui.FragmentDoors

private val TAB_TITLES = arrayOf(
    "Двери",
    "Камеры"
)
class PagerAdapter(private val context: Context, fm: FragmentManager) :
    FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment.
        return when(position) {
            0 -> {
                FragmentDoors()
            }

            1 -> {
                FragmentCameras()
            }

            else -> {
                FragmentDoors()
            }
        }
    }

    override fun getPageTitle(position: Int): CharSequence {
        return TAB_TITLES[position]
    }

    override fun getCount(): Int {
        return TAB_TITLES.size
    }
}