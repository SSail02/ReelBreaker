package com.reelbreaker.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.reelbreaker.databinding.ActivityBlockOverlayBinding

class BlockOverlayActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityBlockOverlayBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnClose.setOnClickListener { finish() }
    }
}
