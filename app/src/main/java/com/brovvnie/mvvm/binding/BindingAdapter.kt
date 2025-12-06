package com.brovvnie.mvvm.binding

import android.view.View
import android.widget.RadioGroup
import androidx.databinding.BindingAdapter
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
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

    /**
     * RadioGroup选项
     */
    @JvmStatic
    @BindingAdapter("checkChildAt")
    fun checkChildAt(radioGroup: RadioGroup, position: Int) {
        radioGroup.check(radioGroup.getChildAt(position).id)
    }

    @JvmStatic
    @BindingAdapter("adapter")
    fun bindAdapter(viewPager: ViewPager2, adapter: FragmentStateAdapter) {
        viewPager.adapter = adapter
    }

    @JvmStatic
    @BindingAdapter("currentItem")
    fun currentItem(viewPager: ViewPager2, position: Int) {
        viewPager.currentItem = position
    }

    @JvmStatic
    @BindingAdapter("registerOnPageChange")
    fun registerOnPageChange(viewPager: ViewPager2, listener: OnPageChangeListener) {
        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                listener.onPageSelected(position)
            }
        })
    }

    interface OnPageChangeListener {
        fun onPageSelected(position: Int)
    }

    @JvmStatic
    @BindingAdapter("setOnCheckedChangeListener")
    fun setOnCheckedChangeListener(radioGroup: RadioGroup, listener: OnPageChangeListener) {
        radioGroup.setOnCheckedChangeListener { radioGroup, checkedId ->
            listener.onPageSelected(radioGroup.indexOfChild(radioGroup.findViewById(checkedId)))
        }
    }
}