package com.brovvnie.mvvm.binding

import android.view.View
import androidx.databinding.BindingAdapter
import com.blankj.utilcode.util.ClickUtils

object BindingAdapter {
    /**
     * 单击防抖
     */
    @JvmStatic
    @BindingAdapter("onClickSingleDebouncing")
    fun onClickSingleDebouncing(view: View, clickListener: View.OnClickListener?) {
        ClickUtils.applySingleDebouncing(view, clickListener)
    }
}