package com.example.w4_app.ui.SplashScreen

import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.w4_app.databinding.ActivitySplashScreenBinding
import com.example.w4_app.ui.main.MainActivity
import com.example.w4_app.utils.Constant

class SplashScreenActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        playAnimation()

        val countdownTimer = object : CountDownTimer(Constant.TIMER_1, Constant.TIMER_2) {
            override fun onTick(millisUntilFinished: Long) {
            }

            override fun onFinish() {
                navigateToMainActivity()
            }
        }
        countdownTimer.start()
    }

    private fun navigateToMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun playAnimation() {
        ObjectAnimator.ofFloat(binding.imageView, View.TRANSLATION_Y, -30f, 30f).apply {
            duration = Constant.SPLASH_SCREEN_TIMER
            repeatCount = ObjectAnimator.INFINITE
            repeatMode = ObjectAnimator.REVERSE
        }.start()
    }

}