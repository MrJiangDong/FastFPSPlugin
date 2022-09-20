package com.jyc.fast.fps

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.jyc.library.fast.fps.FastFpsMonitor

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        FastFpsMonitor.toggle()
    }
}