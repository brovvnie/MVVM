package com.brovvnie.mvvm.utils

import android.content.res.Resources
import android.os.Build
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat

/**
 * 状态栏工具类
 */
object BarUtils {
    /**
     * 防止控件的内容绘制到状态栏区域
     */
    fun statusBarsPadding(v: View) {
        ViewCompat.setOnApplyWindowInsetsListener(v) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.statusBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    /**
     * 添加MarginTop状态栏高度
     */
    fun statusBarsMargin(v: View) {
        ViewCompat.setOnApplyWindowInsetsListener(v) { v, insets ->
            val layoutParams = v.layoutParams as? ViewGroup.MarginLayoutParams
                ?: return@setOnApplyWindowInsetsListener insets
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.statusBars())
            layoutParams.setMargins(
                layoutParams.leftMargin,
                systemBars.top,
                layoutParams.rightMargin,
                layoutParams.bottomMargin
            )
            insets
        }
    }

    /**
     * 添加MarginTop状态栏高度
     */
    fun statusBarsMargin(v: View, topDp: Float) {
        ViewCompat.setOnApplyWindowInsetsListener(v) { v, insets ->
            val layoutParams = v.layoutParams as? ViewGroup.MarginLayoutParams
                ?: return@setOnApplyWindowInsetsListener insets
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.statusBars())
            layoutParams.setMargins(
                layoutParams.leftMargin,
                systemBars.top + dp2px(topDp),
                layoutParams.rightMargin,
                layoutParams.bottomMargin
            )
            insets
        }
    }

    fun dp2px(dpValue: Float): Int {
        val scale = Resources.getSystem().displayMetrics.density
        return (dpValue * scale + 0.5f).toInt()
    }

    /**
     * 防止控件的内容绘制到导航栏区域
     */
    fun navigationBarsPadding(v: View) {
        ViewCompat.setOnApplyWindowInsetsListener(v) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.navigationBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    /**
     * 防止控件的内容绘制到输入法区域
     */
    fun imePadding(v: View) {
        ViewCompat.setOnApplyWindowInsetsListener(v) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.navigationBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    /**
     * 防止控件的内容绘制到状态栏、导航栏区域
     */
    fun systemBarsPadding(v: View) {
        ViewCompat.setOnApplyWindowInsetsListener(v) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    /**
     * 可保护控件的内容不进入 Cutout 区域。（刘海屏）
     */
    fun displayCutoutPadding(v: View) {
        ViewCompat.setOnApplyWindowInsetsListener(v) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.displayCutout())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    /**
     * 避免与系统手势发生冲突。
     */
    fun safeGesturesPadding(v: View) {
        ViewCompat.setOnApplyWindowInsetsListener(v) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemGestures())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    /**
     * 显示或隐藏系统栏
     */
    fun showSystemBar(b: Boolean, window: Window) {
        val windowInsetsController = WindowCompat.getInsetsController(window, window.decorView)
        if (b) {
            // 显示系统栏
            windowInsetsController.show(WindowInsetsCompat.Type.systemBars())
        } else {
            // 隐藏系统栏
            windowInsetsController.hide(WindowInsetsCompat.Type.systemBars())
        }
    }

    /**
     * 改变状态栏颜色
     */
    fun lightStatusBar(b: Boolean, window: Window) {
        WindowCompat.getInsetsController(window, window.decorView)
            .isAppearanceLightStatusBars = b
    }

    /**
     * 改变导航栏颜色
     */
    fun lightNavigationBar(b: Boolean, window: Window) {
        WindowCompat.getInsetsController(window, window.decorView)
            .isAppearanceLightNavigationBars = b
    }

    /**
     * 关闭导航条对比度被强制执行
     */
    fun closeNavigationBarContrast(window: Window) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            window.isNavigationBarContrastEnforced = false
        }
    }
}