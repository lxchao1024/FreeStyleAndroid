package com.lxchao.app.ui.activity

import android.graphics.SurfaceTexture
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.*
import com.lxchao.app.R
import com.lxchao.app.utils.MediaPlayerManager

class SurfaceViewActivity : AppCompatActivity(), TextureView.SurfaceTextureListener {
    override fun onSurfaceTextureSizeChanged(p0: SurfaceTexture?, p1: Int, p2: Int) {
    }

    override fun onSurfaceTextureUpdated(p0: SurfaceTexture?) {
    }

    override fun onSurfaceTextureDestroyed(p0: SurfaceTexture?): Boolean {
        MediaPlayerManager.stopMedia()
        return true
    }

    override fun onSurfaceTextureAvailable(p0: SurfaceTexture?, p1: Int, p2: Int) {
        val path = "http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4"
        MediaPlayerManager.playUrlMedia(Surface(p0), path)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        val textureView = TextureView(this)
        textureView.surfaceTextureListener = this
        textureView.alpha = 0.5f
        setContentView(textureView)
    }
}
