package com.jyc.fast.fps

import android.app.Application
import com.jyc.library.fast.fps.FpsActivityManager


/// @author jyc
/// 创建日期：2021/4/25
/// 描述：MyApplication
class MyApplication : Application() {
    private var isDebug = true
    override fun onCreate() {
        super.onCreate()
        FpsActivityManager.instance.init(this)
    }

}