package com.lxchao.mylibrary.window.listener

import com.lxchao.mylibrary.window.view.FloatingMagnetView

/**
 * Created by liyunpeng on 17/11/29.
 */
interface MagnetViewListener {

    fun onRemove(magnetView: FloatingMagnetView)

    fun onClick(magnetView: FloatingMagnetView)
}
