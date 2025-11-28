package com.brovvnie.mvvm.ui.login;

import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;

import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;

import com.brovvnie.mvvm.BR;
import com.brovvnie.mvvm.R;
import com.brovvnie.mvvm.base.BaseActivity;
import com.brovvnie.mvvm.databinding.ActivityLoginBinding;

/**
 * 一个MVVM模式的登陆界面
 */
public class LoginActivity extends BaseActivity<ActivityLoginBinding, LoginViewModel> {
    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public void initViewObservable() {
        //监听ViewModel中pSwitchObservable的变化, 当ViewModel中执行【uc.pSwitchObservable.set(!uc.pSwitchObservable.get());】时会回调该方法
        viewModel.uc.pSwitchEvent.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable Boolean aBoolean) {
                //pSwitchObservable是boolean类型的观察者,所以可以直接使用它的值改变密码开关的图标
                if (viewModel.uc.pSwitchEvent.getValue()) {
                    //密码可见
                    //在xml中定义id后,使用binding可以直接拿到这个view的引用,不再需要findViewById去找控件了
                    binding.ivSwichPasswrod.setImageResource(R.mipmap.show_psw);
                    binding.etPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else {
                    //密码不可见
                    binding.ivSwichPasswrod.setImageResource(R.mipmap.show_psw_press);
                    binding.etPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });
    }
}
