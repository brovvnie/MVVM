package com.brovvnie.mvvm.ui.main

import androidx.fragment.app.Fragment
import com.brovvnie.mvvm.base.BaseMVVMActivity
import com.brovvnie.mvvm.base.BaseVP2Adapter
import com.brovvnie.mvvm.databinding.ActivityMainBinding
import com.brovvnie.mvvm.ui.device.DeviceFragment
import com.brovvnie.mvvm.ui.home.HomeFragment
import com.brovvnie.mvvm.ui.mine.MineFragment

class MainActivity : BaseMVVMActivity<ActivityMainBinding, MainViewModel>() {
    override fun initView() {
        val fragments = listOf<Fragment>(HomeFragment(), DeviceFragment(), MineFragment())
        binding.adapter = BaseVP2Adapter(supportFragmentManager, lifecycle, fragments)
    }
}