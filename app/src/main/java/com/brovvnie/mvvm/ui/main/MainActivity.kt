package com.brovvnie.mvvm.ui.main

import com.brovvnie.mvvm.BR
import com.brovvnie.mvvm.base.BaseActivity
import com.brovvnie.mvvm.databinding.ActivityMainBinding

class MainActivity: BaseActivity<ActivityMainBinding, MainViewModel>() {
    override fun initVariableId(): Int {
        return BR.vm
    }
}