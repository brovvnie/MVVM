package com.brovvnie.mvvm.binding

import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.view.View
import androidx.activity.OnBackPressedDispatcherOwner
import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.blankj.utilcode.util.ToastUtils
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.google.android.material.card.MaterialCardView

object ViewBinding {

    @JvmStatic
    @BindingAdapter("onClick")
    fun bindOnClick(view: View, clickListener: View.OnClickListener?) {
        clickListener?.let {
            view.setOnClickListener(it)
        }
    }

    @JvmStatic
    @BindingAdapter("toast")
    fun bindToast(view: View, text: String?) {
        ToastUtils.showShort(text)
    }

    @JvmStatic
    @BindingAdapter("paletteImage", "paletteCard")
    fun bindLoadImagePalette(view: AppCompatImageView, url: String, paletteCard: MaterialCardView) {
        Glide.with(view.context)
            .load(url)
            .listener(
                object : RequestListener<Drawable> {
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Drawable>,
                        isFirstResource: Boolean,
                    ): Boolean {
                        return false
                    }

                    override fun onResourceReady(
                        resource: Drawable,
                        model: Any,
                        target: Target<Drawable>?,
                        dataSource: DataSource,
                        isFirstResource: Boolean,
                    ): Boolean {
                        val drawable = resource as BitmapDrawable
                        val bitmap = drawable.bitmap
                        return false
                    }
                },
            ).into(view)
    }

    @JvmStatic
    @BindingAdapter("paletteImage", "paletteView")
    fun bindLoadImagePaletteView(view: AppCompatImageView, url: String, paletteView: View) {
        val context = view.context
        Glide.with(context)
            .load(url)
            .listener(
                object : RequestListener<Drawable> {
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Drawable>,
                        isFirstResource: Boolean,
                    ): Boolean {
                        return false
                    }

                    override fun onResourceReady(
                        resource: Drawable,
                        model: Any,
                        target: Target<Drawable>?,
                        dataSource: DataSource,
                        isFirstResource: Boolean,
                    ): Boolean {
                        val drawable = resource as BitmapDrawable
                        val bitmap = drawable.bitmap
                        return false
                    }
                },
            ).into(view)
    }

    @JvmStatic
    @BindingAdapter("gone")
    fun bindGone(view: View, shouldBeGone: Boolean) {
        view.visibility = if (shouldBeGone) {
            View.GONE
        } else {
            View.VISIBLE
        }
    }

    @JvmStatic
    @BindingAdapter("onBackPressed")
    fun bindOnBackPressed(view: View, onBackPress: Boolean) {
        val context = view.context
        if (onBackPress && context is OnBackPressedDispatcherOwner) {
            view.setOnClickListener {
                context.onBackPressedDispatcher.onBackPressed()
            }
        }
    }

    @JvmStatic
    @BindingAdapter("bindPokemonTypes")
    fun bindPokemonTypes(recyclerView: RecyclerView, types: List<Any>?) {
    }
}
