package com.lxchao.mylibrary.window.view

import android.app.Activity
import android.view.ViewGroup
import android.widget.FrameLayout

import androidx.annotation.DrawableRes

import com.lxchao.mylibrary.window.listener.MagnetViewListener

/**
 * Created by Yunpeng Li on 2018/3/15.
 */

interface IFloatingView {

    val view: EnFloatingView

    fun remove(): FloatingView

    fun add(): FloatingView

    fun attach(activity: Activity): FloatingView

    fun attach(container: FrameLayout): FloatingView

    fun detach(activity: Activity): FloatingView

    fun detach(container: FrameLayout): FloatingView

    fun icon(@DrawableRes resId: Int): FloatingView

    fun layoutParams(params: ViewGroup.LayoutParams): FloatingView

    fun listener(magnetViewListener: MagnetViewListener): FloatingView

}
