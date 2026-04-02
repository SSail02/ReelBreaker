package com.reelbreaker.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.reelbreaker.databinding.ActivitySettingsBinding

class SettingsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}
