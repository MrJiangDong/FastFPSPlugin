package com.jyc.library.fast.fps

import android.annotation.SuppressLint
import android.app.Application
import java.lang.Exception


/// @author jyc
/// 创建日期：2021/8/11
/// 描述：AppGlobals
object FpsAppGlobals {

    private var application: Application? = null

    @SuppressLint("PrivateApi", "DiscouragedPrivateApi")
    fun get(): Application? {
        if (application == null) {
            try {
//                application = Class.forName("android.app.ActivityThread")
//                    .getMethod("currentApplication")
//                    .invoke(null) as Application


                val activityThread = Class.forName("android.app.ActivityThread")
                val currentApplication = activityThread.getDeclaredMethod("currentApplication")
                val currentActivityThread =
                    activityThread.getDeclaredMethod("currentActivityThread")
                val current = currentActivityThread.invoke(null as Any?)
                application = currentApplication.invoke(current) as Application?
            } catch (ex: Exception) {
                ex.printStackTrace()
            }

        }

        return application
    }
}