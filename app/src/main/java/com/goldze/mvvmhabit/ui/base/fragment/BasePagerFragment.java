package com.goldze.mvvmhabit.ui.base.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.goldze.mvvmhabit.R;
import com.goldze.mvvmhabit.base.BaseFragment;
import com.goldze.mvvmhabit.base.BaseViewModel;
import com.goldze.mvvmhabit.databinding.FragmentBasePagerBinding;
import com.goldze.mvvmhabit.ui.base.adapter.BaseFragmentPagerAdapter;
import com.google.android.material.tabs.TabLayout;

import java.util.List;

/**
 * Created by goldze on 2017/7/17.
 * 抽取的二级BasePagerFragment
 */

public abstract class BasePagerFragment extends BaseFragment<FragmentBasePagerBinding, BaseViewModel> {

    private List<Fragment> mFragments;
    private List<String> titlePager;

    protected abstract List<Fragment> pagerFragment();

    protected abstract List<String> pagerTitleString();

    @Override
    public int initContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return R.layout.fragment_base_pager;
    }

    @Override
    public int initVariableId() {
        return 0;
    }

    @Override
    public void initData() {
        mFragments = pagerFragment();
        titlePager = pagerTitleString();
        //设置Adapter
        BaseFragmentPagerAdapter pagerAdapter = new BaseFragmentPagerAdapter(getChildFragmentManager(), mFragments, titlePager);
        binding.viewPager.setAdapter(pagerAdapter);
        binding.tabs.setupWithViewPager(binding.viewPager);
        binding.viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(binding.tabs));
    }

    @Override
    public void initViewObservable() {

    }
}
