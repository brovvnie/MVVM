package com.brovvnie.mvvm.ui.rv_multi;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

import com.brovvnie.mvvm.BR;
import com.brovvnie.mvvm.R;
import com.brovvnie.mvvm.base.BaseFragment;
import com.brovvnie.mvvm.databinding.FragmentMultiRvBinding;

/**
 * Create Author：goldze
 * Create Date：2019/01/25
 * Description：RecycleView多布局实现
 */

public class MultiRecycleViewFragment extends BaseFragment<FragmentMultiRvBinding, MultiRecycleViewModel> {
    @Override
    public int initContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return R.layout.fragment_multi_rv;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public void initData() {
        super.initData();
    }
}
