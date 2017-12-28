package com.example.chris.viewdrawanimation

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var isAnimating = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener {
            if (isAnimating) {
                customView.reset()
            } else {
                customView.animateView()
            }

            isAnimating = !isAnimating
        }
    }
}
