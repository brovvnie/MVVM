package com.brovvnie.mvvm.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.brovvnie.mvvm.BR
import java.lang.reflect.ParameterizedType

@Suppress("UNCHECKED_CAST")
abstract class BaseMVVMFragment<V : ViewDataBinding, VM : BaseViewModel> : Fragment() {
    lateinit var binding: V
    lateinit var viewModel: VM
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // 反射拿到泛型初始化ViewDataBinding和ViewModel
        val type = javaClass.getGenericSuperclass() as ParameterizedType
        val vc = type.actualTypeArguments[0] as Class<V>
        val vmc = type.actualTypeArguments[1] as Class<VM>
        val method = vc.getDeclaredMethod("inflate", LayoutInflater::class.java)
        binding = method.invoke(null, layoutInflater) as V
        viewModel = ViewModelProvider(this)[vmc]
        //支持LiveData绑定xml，数据改变，UI自动会更新
        binding.lifecycleOwner = this
        //让ViewModel拥有View的生命周期感应
        lifecycle.addObserver(viewModel)
        //绑定viewModel
        binding.setVariable(BR.vm, viewModel)
        initView()
        return binding.root
    }

    open fun initView() {}
}