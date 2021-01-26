package com.example.gsonrecyclerimage.Activity

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.airbnb.lottie.LottieAnimationView
import com.example.gsonrecyclerimage.R


class TargetItemActivity : AppCompatActivity() {
    private var lottie: LottieAnimationView? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_target_item)

        val text = intent.getStringExtra("title")
        val textView: TextView = findViewById(R.id.textViewSecond)
        textView.text = text


        lottie = findViewById(R.id.buttonViewLottie)
        lottie!!.setOnClickListener {
            lottie!!.pauseAnimation();
            lottie!!.playAnimation()
            lottie!!.isClickable = false
        }


    }
}