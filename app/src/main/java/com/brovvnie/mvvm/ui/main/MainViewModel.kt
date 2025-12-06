package com.brovvnie.mvvm.ui.main

import androidx.lifecycle.MutableLiveData
import com.brovvnie.mvvm.base.BaseViewModel

class MainViewModel() : BaseViewModel() {
    var page = MutableLiveData(0)
    fun pageChange(position: Int) {
        page.value = position
    }
}