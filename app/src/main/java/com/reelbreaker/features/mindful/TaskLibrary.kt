package com.reelbreaker.features.mindful

import kotlin.random.Random

object TaskLibrary {
    val tasks: List<MindfulTask> = listOf(
        MindfulTask("Step outside and take 10 deep breaths", TaskCategory.NATURE, "🌿", 120),
        MindfulTask("Look at the sky for one minute", TaskCategory.NATURE, "☁️", 60),
        MindfulTask("Water one plant", TaskCategory.NATURE, "🪴", 90),
        MindfulTask("Open a window and feel fresh air", TaskCategory.NATURE, "🍃", 60),
        MindfulTask("Stretch your neck and shoulders", TaskCategory.BODY, "🧘", 90),
        MindfulTask("20-second wall sit", TaskCategory.BODY, "💪", 20),
        MindfulTask("Do 10 squats", TaskCategory.BODY, "🏃", 60),
        MindfulTask("Drink a glass of water", TaskCategory.BODY, "💧", 45),
        MindfulTask("Wash your face with cool water", TaskCategory.BODY, "🫧", 60),
        MindfulTask("Tidy your desk for 2 minutes", TaskCategory.HOME, "🧹", 120),
        MindfulTask("Fold three clothes", TaskCategory.HOME, "👕", 90),
        MindfulTask("Clean your phone screen", TaskCategory.HOME, "📱", 45),
        MindfulTask("Throw away one piece of clutter", TaskCategory.HOME, "🗑️", 45),
        MindfulTask("Send a kind message to a friend", TaskCategory.SOCIAL, "💬", 90),
        MindfulTask("Check in with family briefly", TaskCategory.SOCIAL, "❤️", 120),
        MindfulTask("Thank someone mentally right now", TaskCategory.SOCIAL, "🙏", 40),
        MindfulTask("Note three things you feel", TaskCategory.MIND, "🧠", 100),
        MindfulTask("Close eyes and breathe 4-4-4", TaskCategory.MIND, "😌", 90),
        MindfulTask("Write one sentence of gratitude", TaskCategory.MIND, "✍️", 100),
        MindfulTask("Name 5 things you can see", TaskCategory.MIND, "👀", 80),
        MindfulTask("Listen to silence for 30 seconds", TaskCategory.MIND, "🔕", 30),
        MindfulTask("Do one random act of order", TaskCategory.RANDOM, "🎯", 120),
        MindfulTask("Stand up and reset posture", TaskCategory.BODY, "🪑", 50),
        MindfulTask("Stretch your wrists", TaskCategory.BODY, "🤲", 50),
        MindfulTask("Walk to another room and back", TaskCategory.BODY, "🚶", 70),
        MindfulTask("Refill your water bottle", TaskCategory.HOME, "🚰", 70),
        MindfulTask("Wipe kitchen counter", TaskCategory.HOME, "🧽", 100),
        MindfulTask("Organize one app folder", TaskCategory.HOME, "🗂️", 90),
        MindfulTask("Compliment yourself out loud", TaskCategory.MIND, "💛", 45),
        MindfulTask("Observe your breathing rhythm", TaskCategory.MIND, "🌬️", 90),
        MindfulTask("Smile intentionally for 10 seconds", TaskCategory.MIND, "🙂", 20),
        MindfulTask("Look at a distant object", TaskCategory.NATURE, "🏞️", 50),
        MindfulTask("Watch sunlight/shadow for a minute", TaskCategory.NATURE, "🌞", 60),
        MindfulTask("Open balcony/door and stretch", TaskCategory.NATURE, "🚪", 80),
        MindfulTask("Do 15 jumping jacks", TaskCategory.BODY, "⚡", 40),
        MindfulTask("Massage your temples", TaskCategory.BODY, "🤍", 50),
        MindfulTask("Roll shoulders slowly", TaskCategory.BODY, "🔄", 45),
        MindfulTask("Set out tomorrow's clothes", TaskCategory.HOME, "👖", 90),
        MindfulTask("Clear 5 unread notifications", TaskCategory.HOME, "📥", 60),
        MindfulTask("Put one thing back in place", TaskCategory.HOME, "📌", 40),
        MindfulTask("Text someone: ‘thinking of you’", TaskCategory.SOCIAL, "🤝", 70),
        MindfulTask("React to one message mindfully", TaskCategory.SOCIAL, "📨", 60),
        MindfulTask("Share one genuine thank you", TaskCategory.SOCIAL, "🌟", 50),
        MindfulTask("Note your top priority for today", TaskCategory.MIND, "🗒️", 80),
        MindfulTask("Repeat a calming phrase", TaskCategory.MIND, "🕊️", 60),
        MindfulTask("Count backward from 30 slowly", TaskCategory.MIND, "🔢", 70),
        MindfulTask("Do 1 minute of silence", TaskCategory.MIND, "⏳", 60),
        MindfulTask("Take 8 mindful steps", TaskCategory.BODY, "👣", 45),
        MindfulTask("Do a gentle back stretch", TaskCategory.BODY, "🧍", 60),
        MindfulTask("Observe one sound around you", TaskCategory.NATURE, "🎧", 50),
        MindfulTask("Stand near natural light", TaskCategory.NATURE, "🪟", 40),
        MindfulTask("Rearrange one shelf item", TaskCategory.HOME, "📚", 80),
        MindfulTask("Delete one distracting app shortcut", TaskCategory.HOME, "📴", 75)
    )

    fun getRandom(): MindfulTask = tasks.random()

    fun getRandomFromCategory(category: TaskCategory): MindfulTask {
        val filtered = tasks.filter { it.category == category }
        return if (filtered.isNotEmpty()) filtered.random() else getRandom()
    }

    fun byIndex(index: Int): MindfulTask = tasks[index.coerceIn(0, tasks.lastIndex)]
    fun randomIndex(exclude: Int = -1): Int {
        if (tasks.size <= 1) return 0
        var i: Int
        do i = Random.nextInt(tasks.size) while (i == exclude)
        return i
    }
}
