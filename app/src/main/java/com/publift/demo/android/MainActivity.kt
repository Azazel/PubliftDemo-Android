package com.publift.demo.android

import android.os.Bundle
import android.widget.Button
import androidx.activity.ComponentActivity
import com.inmobi.cmp.ChoiceCmp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button1 = findViewById<Button>(R.id.button1)
        button1.setOnClickListener {
            ChoiceCmp.forceDisplayUI(this)
        }
    }
}
