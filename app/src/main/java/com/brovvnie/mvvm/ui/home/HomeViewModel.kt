package com.brovvnie.mvvm.ui.home

import android.util.Log
import androidx.lifecycle.LifecycleOwner
import com.brovvnie.mvvm.api.Api
import com.brovvnie.mvvm.base.BaseViewModel
import com.brovvnie.mvvm.bean.ArticleBean
import com.brovvnie.mvvm.utils.NetUtils
import io.reactivex.rxjava3.disposables.Disposable

class HomeViewModel : BaseViewModel() {
    companion object {
        private const val TAG = "HomeViewModel"
    }

    override fun onCreate(owner: LifecycleOwner) {
        NetUtils.getInstance()
            .get(Api.ARTICLE+"0/json", ArticleBean::class.java, object : NetUtils.NetCallback {
                override fun netSuccess(o: Any?) {
                    if (o is ArticleBean) {
                        Log.i(TAG, "netSuccess: $o")
                    }
                }

                override fun netError(t: Throwable?) {
                }

                override fun netDisposable(disposable: Disposable?) {
                }
            })
    }
}