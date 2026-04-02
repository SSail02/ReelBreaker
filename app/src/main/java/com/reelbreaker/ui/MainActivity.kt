package com.reelbreaker.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.reelbreaker.databinding.ActivityMainBinding
import com.reelbreaker.service.ForegroundMonitorService
import com.reelbreaker.ui.viewmodel.MainViewModel
import com.reelbreaker.util.PermissionUtils
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val vm: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnEnableAccessibility.setOnClickListener { PermissionUtils.openAccessibilitySettings(this) }
        binding.btnStats.setOnClickListener { startActivity(Intent(this, StatsActivity::class.java)) }
        binding.btnSettings.setOnClickListener { startActivity(Intent(this, SettingsActivity::class.java)) }
        startForegroundService(Intent(this, ForegroundMonitorService::class.java))

        lifecycleScope.launch {
            vm.blockedCount.collect { binding.tvBlockedCount.text = it.toString() }
        }
        lifecycleScope.launch {
            vm.completedCount.collect { binding.tvTaskCount.text = it.toString() }
        }
    }
}
