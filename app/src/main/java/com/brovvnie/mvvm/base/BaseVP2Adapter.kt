package com.brovvnie.mvvm.base

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

/**
 * ViewPager2的Fragment适配器
 */
class BaseVP2Adapter(manager: FragmentManager, lifecycle: Lifecycle, val list: List<Fragment>) :
    FragmentStateAdapter(manager, lifecycle) {
    override fun createFragment(position: Int): Fragment {
        return list[position]
    }

    override fun getItemCount(): Int {
        return list.size
    }
}