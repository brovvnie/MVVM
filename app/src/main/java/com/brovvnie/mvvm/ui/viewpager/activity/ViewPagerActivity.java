package com.brovvnie.mvvm.ui.viewpager.activity;

import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;

import com.blankj.utilcode.util.ToastUtils;
import com.brovvnie.mvvm.BR;
import com.brovvnie.mvvm.base.BaseActivity;
import com.brovvnie.mvvm.databinding.FragmentViewpagerBinding;
import com.brovvnie.mvvm.ui.viewpager.vm.ViewPagerViewModel;
import com.google.android.material.tabs.TabLayout;

/**
 * ViewPager绑定的例子, 更多绑定方式，请参考 https://github.com/evant/binding-collection-adapter
 * 所有例子仅做参考,千万不要把它当成一种标准,毕竟主打的不是例子,业务场景繁多,理解如何使用才最重要。
 * Created by goldze on 2018/7/18.
 */

public class ViewPagerActivity extends BaseActivity<FragmentViewpagerBinding, ViewPagerViewModel> {

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }


    @Override
    public void initData() {
        // 使用 TabLayout 和 ViewPager 相关联
        binding.tabs.setupWithViewPager(binding.viewPager);
        binding.viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(binding.tabs));
        //给ViewPager设置adapter

    }

    @Override
    public void initViewObservable() {
        viewModel.itemClickEvent.observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String text) {
                ToastUtils.showShort("position：" + text);
            }
        });
    }
}
