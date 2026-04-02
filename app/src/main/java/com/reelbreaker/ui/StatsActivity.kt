package com.reelbreaker.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.reelbreaker.databinding.ActivityStatsBinding
import com.reelbreaker.ui.adapters.SimpleMapAdapter
import com.reelbreaker.ui.viewmodel.StatsViewModel
import kotlinx.coroutines.launch

class StatsActivity : AppCompatActivity() {
    private val vm: StatsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityStatsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvCategory.adapter = SimpleMapAdapter()
        binding.rvDaily.adapter = SimpleMapAdapter()

        lifecycleScope.launch { vm.totalBlocked.collect { binding.tvTotalBlocked.text = it.toString() } }
        lifecycleScope.launch { vm.completedTasks.collect { binding.tvTasksCompleted.text = it.toString() } }
        lifecycleScope.launch {
            vm.categoryBreakdown.collect { (binding.rvCategory.adapter as SimpleMapAdapter).submit(it) }
        }
        lifecycleScope.launch {
            vm.dailyUsage.collect { (binding.rvDaily.adapter as SimpleMapAdapter).submit(it) }
        }
        vm.refresh()
    }
}
