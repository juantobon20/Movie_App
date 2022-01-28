package com.appinc.movieapp.views.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.appinc.movieapp.databinding.ActivitySplashBinding
import com.appinc.movieapp.views.main.MainActivity

class SplashActivity : AppCompatActivity() {

    private var _binding: ActivitySplashBinding? = null
    private lateinit var viewModel: SplashViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        this._binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(this._binding?.root)

        this.viewModel = ViewModelProvider(this)[SplashViewModel::class.java]
        this.viewModel.success.observe(this) {
            _binding?.animationView?.pauseAnimation()
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}