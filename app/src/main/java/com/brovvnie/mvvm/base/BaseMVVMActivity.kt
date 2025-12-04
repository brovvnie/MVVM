package com.brovvnie.mvvm.base

import android.os.Bundle
import android.view.LayoutInflater
import androidx.activity.enableEdgeToEdge
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import com.brovvnie.mvvm.BR
import com.brovvnie.mvvm.utils.BarUtils
import java.lang.reflect.ParameterizedType

@Suppress("UNCHECKED_CAST")
abstract class BaseMVVMActivity<V : ViewDataBinding, VM : BaseViewModel> : FragmentActivity() {
    lateinit var binding: V
    lateinit var viewModel: VM
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 反射拿到泛型初始化ViewDataBinding和ViewModel
        val type = javaClass.getGenericSuperclass() as ParameterizedType
        val vc = type.actualTypeArguments[0] as Class<V>
        val vmc = type.actualTypeArguments[1] as Class<VM>
        val method = vc.getDeclaredMethod("inflate", LayoutInflater::class.java)
        binding = method.invoke(null, layoutInflater) as V
        setContentView(binding.root)
        viewModel = ViewModelProvider(this)[vmc]
        // 适配Android15系统栏
        enableEdgeToEdge()
        BarUtils.navigationBarsPadding(binding.root)
        //支持LiveData绑定xml，数据改变，UI自动会更新
        binding.setLifecycleOwner(this)
        //让ViewModel拥有View的生命周期感应
        lifecycle.addObserver(viewModel)
        //绑定viewModel
        binding.setVariable(BR.vm, viewModel)
    }
}