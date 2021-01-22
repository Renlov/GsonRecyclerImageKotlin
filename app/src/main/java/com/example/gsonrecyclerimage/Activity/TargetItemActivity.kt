package com.example.gsonrecyclerimage.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.gsonrecyclerimage.R

class TargetItemActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_target_item)

        val text = intent.getStringExtra("title")
        val textView: TextView = findViewById(R.id.textViewSecond)
        textView.text = text

    }
}