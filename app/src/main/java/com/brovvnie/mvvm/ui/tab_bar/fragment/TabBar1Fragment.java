package com.brovvnie.mvvm.ui.tab_bar.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

import com.brovvnie.mvvm.BR;
import com.brovvnie.mvvm.R;
import com.brovvnie.mvvm.base.BaseFragment;


/**
 * Created by goldze on 2018/7/18.
 */

public class TabBar1Fragment extends BaseFragment {
    @Override
    public int initContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return R.layout.fragment_tab_bar_1;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }
}
