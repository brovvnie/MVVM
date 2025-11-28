package com.brovvnie.mvvm.ui.network.detail;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;
import androidx.lifecycle.LifecycleOwner;

import com.brovvnie.mvvm.base.BaseViewModel;
import com.brovvnie.mvvm.entity.DemoEntity;

/**
 * Created by goldze on 2017/7/17.
 */

public class DetailViewModel extends BaseViewModel {
    public ObservableField<DemoEntity.ItemsEntity> entity = new ObservableField<>();

    public DetailViewModel(@NonNull Application application) {

    }

    public void setDemoEntity(DemoEntity.ItemsEntity entity) {
        this.entity.set(entity);
    }

    @Override
    public void onDestroy(@NonNull LifecycleOwner owner) {
        super.onDestroy(owner);
        entity = null;
    }
}
