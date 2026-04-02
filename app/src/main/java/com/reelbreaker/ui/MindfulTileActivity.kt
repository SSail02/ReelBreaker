package com.reelbreaker.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.reelbreaker.databinding.ActivityMindfulTileBinding
import com.reelbreaker.features.mindful.MindfulTask
import com.reelbreaker.ui.viewmodel.MindfulTileViewModel

class MindfulTileActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMindfulTileBinding
    private val vm: MindfulTileViewModel by viewModels()
    private lateinit var task: MindfulTask

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMindfulTileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        task = vm.currentTask()
        binding.tvEmoji.text = task.emoji
        binding.tvTask.text = task.taskText
        binding.tvCategory.text = task.category.name
        binding.tvDuration.text = "~${task.durationHintSecs / 60} min"

        binding.btnDone.setOnClickListener {
            binding.progress.isVisible = true
            vm.complete(task)
            finish()
        }
        binding.btnSnooze.setOnClickListener {
            vm.snooze(task)
            finish()
        }
        binding.btnStayOffline.setOnClickListener {
            vm.skip(task)
            finish()
        }
    }
}
