package com.brovvnie.mvvm.ui.login;

import android.text.TextUtils;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;
import androidx.databinding.ObservableInt;
import androidx.lifecycle.LifecycleOwner;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.brovvnie.mvvm.api.Api;
import com.brovvnie.mvvm.base.BaseViewModel;
import com.brovvnie.mvvm.bean.LoginBean;
import com.brovvnie.mvvm.binding.command.BindingAction;
import com.brovvnie.mvvm.binding.command.BindingCommand;
import com.brovvnie.mvvm.binding.command.BindingConsumer;
import com.brovvnie.mvvm.bus.event.SingleLiveEvent;
import com.brovvnie.mvvm.ui.main.MainActivity;
import com.brovvnie.mvvm.utils.NetUtils;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.rxjava3.disposables.Disposable;

public class LoginViewModel extends BaseViewModel {
    //用户名的绑定
    public ObservableField<String> userName = new ObservableField<>("18613892053");
    //密码的绑定
    public ObservableField<String> password = new ObservableField<>("liuyw68.");
    //用户名清除按钮的显示隐藏绑定
    public ObservableInt clearBtnVisibility = new ObservableInt();
    //封装一个界面发生改变的观察者
    public UIChangeObservable uc = new UIChangeObservable();

    @Override
    public void onCreate(@NonNull LifecycleOwner owner) {

    }

    public class UIChangeObservable {
        //密码开关观察者
        public SingleLiveEvent<Boolean> pSwitchEvent = new SingleLiveEvent<>();
    }

    //清除用户名的点击事件, 逻辑从View层转换到ViewModel层
    public BindingCommand clearUserNameOnClickCommand = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            userName.set("");
        }
    });
    //密码显示开关  (你可以尝试着狂按这个按钮,会发现它有防多次点击的功能)
    public BindingCommand passwordShowSwitchOnClickCommand = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            //让观察者的数据改变,逻辑从ViewModel层转到View层，在View层的监听则会被调用
            uc.pSwitchEvent.setValue(uc.pSwitchEvent.getValue() == null || !uc.pSwitchEvent.getValue());
        }
    });
    //用户名输入框焦点改变的回调事件
    public BindingCommand<Boolean> onFocusChangeCommand = new BindingCommand<>(new BindingConsumer<Boolean>() {
        @Override
        public void call(Boolean hasFocus) {
            if (hasFocus) {
                clearBtnVisibility.set(View.VISIBLE);
            } else {
                clearBtnVisibility.set(View.INVISIBLE);
            }
        }
    });
    //登录按钮的点击事件
    public BindingCommand loginOnClickCommand = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            login();
        }
    });

    public void imgClick() {
        ToastUtils.showShort("点击了图片");
    }

    /**
     * 网络模拟一个登陆操作
     **/
    public void login() {
        String name = userName.get();
        String pwd = password.get();
        if (TextUtils.isEmpty(name)) {
            ToastUtils.showShort("请输入账号！");
        } else if (TextUtils.isEmpty(pwd)) {
            ToastUtils.showShort("请输入密码！");
        } else {
            Map<String, Object> map = new HashMap<>();
            map.put("username", name);
            map.put("password", pwd);
            NetUtils.getInstance().postForm(Api.LOGIN, LoginBean.class, map, new NetUtils.NetCallback() {
                @Override
                public void netSuccess(Object o) {
                    if (o instanceof LoginBean bean) {
                        if (bean.getErrorCode() == 0) {
                            ToastUtils.showShort("登录成功");
                            ActivityUtils.startActivity(MainActivity.class);
                        } else ToastUtils.showShort(bean.getErrorMsg());
                    }
                }

                @Override
                public void netError(Throwable t) {

                }

                @Override
                public void netDisposable(Disposable disposable) {

                }
            });
        }
    }
}
