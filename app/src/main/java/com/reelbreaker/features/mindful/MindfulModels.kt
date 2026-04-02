package com.reelbreaker.features.mindful

enum class TaskCategory { NATURE, BODY, HOME, SOCIAL, MIND, RANDOM }

data class MindfulTask(
    val taskText: String,
    val category: TaskCategory,
    val emoji: String,
    val durationHintSecs: Int
)
