package com.brovvnie.mvvm.ui.rv_multi;

import androidx.annotation.NonNull;

import com.blankj.utilcode.util.ToastUtils;
import com.brovvnie.mvvm.base.BaseViewModel;
import com.brovvnie.mvvm.base.MultiItemViewModel;
import com.brovvnie.mvvm.binding.command.BindingAction;
import com.brovvnie.mvvm.binding.command.BindingCommand;
/**
 * Create Author：goldze
 * Create Date：2019/01/25
 * Description：
 */

public class MultiRecycleHeadViewModel extends MultiItemViewModel {

    public MultiRecycleHeadViewModel(@NonNull BaseViewModel viewModel) {
        super(viewModel);
    }

    //条目的点击事件
    public BindingCommand itemClick = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            ToastUtils.showShort("我是头布局");
        }
    });
}
