package com.brovvnie.mvvm.ui.rv_multi;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableList;

import com.brovvnie.mvvm.base.BaseViewModel;
import com.brovvnie.mvvm.base.MultiItemViewModel;
/**
 * Create Author：goldze
 * Create Date：2019/01/25
 * Description：
 */

public class MultiRecycleViewModel extends BaseViewModel {
    private static final String MultiRecycleType_Head = "head";
    private static final String MultiRecycleType_Left = "left";
    private static final String MultiRecycleType_Right = "right";

    public MultiRecycleViewModel(@NonNull Application application) {
        //模拟10个条目，数据源可以来自网络
        for (int i = 0; i < 20; i++) {
            if (i == 0) {
                MultiItemViewModel item = new MultiRecycleHeadViewModel(this);
                //条目类型为头布局
                item.multiItemType(MultiRecycleType_Head);
                observableList.add(item);
            } else {
                String text = "我是第" + i + "条";
                if (i % 2 == 0) {
                    MultiItemViewModel item = new MultiRecycleLeftItemViewModel(this, text);
                    //条目类型为左布局
                    item.multiItemType(MultiRecycleType_Left);
                    observableList.add(item);
                } else {
                    MultiItemViewModel item = new MultiRecycleRightItemViewModel(this, text);
                    //条目类型为右布局
                    item.multiItemType(MultiRecycleType_Right);
                    observableList.add(item);
                }
             }
        }
    }

    //给RecyclerView添加ObservableList
    public ObservableList<MultiItemViewModel> observableList = new ObservableArrayList<>();
}
