package com.reelbreaker.service

import android.accessibilityservice.AccessibilityService
import android.view.accessibility.AccessibilityEvent
import com.reelbreaker.ReelBreakerApp
import com.reelbreaker.features.blocking.BlockController
import com.reelbreaker.features.blocking.ReelsDetector
import com.reelbreaker.features.blocking.SessionManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ReelBlockerAccessibilityService : AccessibilityService() {
    private val detector = ReelsDetector()
    private val sessionManager by lazy { SessionManager((application as ReelBreakerApp).prefs) }
    private val blockController by lazy { BlockController(this, sessionManager) }
    private var lastTriggerAt = 0L

    override fun onAccessibilityEvent(event: AccessibilityEvent?) {
        if (event?.packageName?.toString() != INSTAGRAM_PACKAGE) return
        val now = System.currentTimeMillis()
        if (now - lastTriggerAt < 1500L) return
        val root = rootInActiveWindow ?: return
        if (!detector.isReelsScreen(root)) return

        lastTriggerAt = now
        val launched = blockController.handleDetectedReels()
        if (launched) {
            CoroutineScope(Dispatchers.IO).launch {
                (application as ReelBreakerApp).repository.logBlock("Instagram", "Reels detected")
                (application as ReelBreakerApp).repository.logUsage("avoided_reels", 1)
            }
        }
    }

    override fun onInterrupt() = Unit

    companion object {
        private const val INSTAGRAM_PACKAGE = "com.instagram.android"
    }
}
