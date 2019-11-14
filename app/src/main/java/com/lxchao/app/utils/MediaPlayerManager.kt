package com.lxchao.app.utils

import android.content.res.AssetFileDescriptor
import android.media.MediaPlayer
import android.view.Surface

/**
 *
 * @author lixiangchao
 * @date 2019/11/14
 * @version 1.0.0
 */
object MediaPlayerManager {

    private var mPlayer: MediaPlayer? = null

    /**
     * 播放网络或本地中的Media资源
     */
    fun playUrlMedia(surface: Surface, mediaPath: String) {
        try {
            if (mPlayer == null) {
                mPlayer = MediaPlayer()
                mPlayer?.setDataSource(mediaPath)
            } else {
                if (mPlayer!!.isPlaying) {
                    mPlayer?.stop()
                }
                mPlayer?.reset()
                mPlayer?.setDataSource(mediaPath)
            }
            mPlayer!!.setSurface(surface)
            mPlayer!!.setVolume(0.5f, 0.5f)
            mPlayer!!.setLooping(true)
            mPlayer!!.prepare()
            mPlayer!!.setOnPreparedListener{ mediaPlayer -> mediaPlayer.start() }
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    /**
     * 播放Asset中的Media资源
     */
    fun playAssetMedia(surface: Surface, fileDescriptor: AssetFileDescriptor) {
        try {
            if (mPlayer == null) {
                mPlayer = MediaPlayer()
                mPlayer!!.setDataSource(fileDescriptor.fileDescriptor, fileDescriptor.startOffset, fileDescriptor.declaredLength)
            } else {
                if (mPlayer!!.isPlaying()) {
                    mPlayer!!.stop()
                }
                mPlayer!!.reset()
                mPlayer!!.setDataSource(fileDescriptor.fileDescriptor, fileDescriptor.startOffset, fileDescriptor.declaredLength)
            }
            mPlayer!!.setSurface(surface)
            mPlayer!!.setVolume(0.5f, 0.5f)
            mPlayer!!.setLooping(true)
            mPlayer!!.prepareAsync()
            mPlayer!!.setOnPreparedListener{ mediaPlayer -> mediaPlayer.start() }
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    /**
     * 停止播放Media
     */
    fun stopMedia() {
        try {
            mPlayer?.let {
                it.isPlaying?:it.stop()
                it.release()
            }
            mPlayer = null
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }
}