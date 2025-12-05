package com.brovvnie.mvvm.ui.main

import android.os.Bundle
import com.brovvnie.mvvm.base.BaseActivity
import com.brovvnie.mvvm.databinding.ActivityMainBinding

class MainActivity: BaseActivity<ActivityMainBinding, MainViewModel>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}