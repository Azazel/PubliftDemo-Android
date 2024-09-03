package com.publift.demo.android

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.ComponentActivity
import com.inmobi.cmp.ChoiceCmp
import com.publift.fuseappsdk.FuseSDK
import com.publift.fuseappsdk.utils.FuseLoggingUtil
import com.publift.fuseappsdk.utils.FuseLoggingUtil.LogLevel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button1 = findViewById<Button>(R.id.button1)
        button1.setOnClickListener {
            startActivity(Intent(this, BannerActivity::class.java))
        }

        val button2 = findViewById<Button>(R.id.button2)
        button2.setOnClickListener {
            startActivity(Intent(this, ListActivity::class.java))
        }

        val button3 = findViewById<Button>(R.id.button3)
        button3.setOnClickListener {
            ChoiceCmp.forceDisplayUI(this)
        }
    }
}
