package com.jyc.library.fast.fps

import android.view.Choreographer
import java.util.concurrent.TimeUnit


/// @author jyc
/// 创建日期：2022/9/9
/// 描述：FrameMonitor
internal class FrameMonitor : Choreographer.FrameCallback {
    private val choreographer = Choreographer.getInstance()
    private var frameStartTime: Long = 0//这个是记录 上一帧到达的时间戳
    private var frameCount = 0//1s 内确切绘制了多少帧

    private val listeners = arrayListOf<FastFpsMonitor.FpsCallback>()

    override fun doFrame(frameTimeNanos: Long) {
        val currentTimeMills: Long = TimeUnit.NANOSECONDS.toMillis(frameTimeNanos)
        if (frameStartTime > 0) {
            //计算两帧之间的 时间差
            //500ms 100ms
            val timeSpan = currentTimeMills - frameStartTime
            //fps 每秒多少帧 frame per second
            frameCount++
            if (timeSpan > 1000) {
                val fps = frameCount * 1000 / timeSpan.toDouble()
//                FastLog.e("FrameMonitor", fps)
                listeners.forEach {
                    it.onFrame(fps)
                }
                frameCount = 0
                frameStartTime = currentTimeMills
            }
        } else {
            frameStartTime = currentTimeMills
        }

        start()
    }

    fun start() {
        choreographer.postFrameCallback(this)
    }

    fun stop() {
        frameStartTime = 0
        listeners.clear()
        choreographer.removeFrameCallback(this)
    }

    fun addListener(l: FastFpsMonitor.FpsCallback) {
        listeners.add(l)
    }
}