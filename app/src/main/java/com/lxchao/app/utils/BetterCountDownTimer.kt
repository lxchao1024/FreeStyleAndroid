package com.guagua.live.utils

import android.annotation.SuppressLint
import android.os.Handler
import android.os.Message
import android.os.SystemClock

/**
 * 解决系统的倒计时Timer最后一秒不回调的问题
 * @author lixiangchao
 * @date 2019/10/10
 * @version 1.0.0
 */
abstract class BetterCountDownTimer(private val millisInFuture: Long, private val countDownInterval: Long) {

    companion object {
        private const val MSG = 1
    }

    /**
     * stop time in future
     */
    private var mStopTimeInFuture: Long = 0

    /**
     * boolean representing if the timer was cancelled
     */
    private var mCancelled = false

    private val mHandler = @SuppressLint("HandlerLeak")
    object: Handler() {
        override fun handleMessage(msg: Message?) {
            synchronized(this@BetterCountDownTimer) {
                if (mCancelled) {
                    return
                }
                val millisLeft = mStopTimeInFuture - SystemClock.elapsedRealtime()
                if (millisLeft <= 0) {
                    onFinish()
                } else {
                    val lastTickStart = SystemClock.elapsedRealtime()
                    onTick(millisLeft)
                    // take into account user's onTick taking time to execute
                    var delay = lastTickStart + countDownInterval - SystemClock.elapsedRealtime()
                    // special case: user's onTick took more than interval to
                    // complete, skip to next interval
                    while (delay < 0) {
                        delay += countDownInterval
                    }
                    sendMessageDelayed(obtainMessage(MSG), delay)
                }
            }
        }
    }

    /**
     * Cancel the countdown.
     */
    @Synchronized
    fun cancel() {
        mCancelled = true
        mHandler.removeMessages(MSG)
    }

    /**
     * Start the countdown.
     */
    @Synchronized
    fun start(): BetterCountDownTimer {
        mCancelled = false
        if (millisInFuture <= 0) {
            onFinish()
            return this
        }
        mStopTimeInFuture = SystemClock.elapsedRealtime() + millisInFuture
        mHandler.sendMessage(mHandler.obtainMessage(MSG))
        return this
    }

    /**
     * Callback fired on regular interval.
     * @param millisUntilFinished The amount of time until finished.
     */
    abstract fun onTick(millisUntilFinished: Long)

    /**
     * Callback fired when the time is up.
     */
    abstract fun onFinish()
}