package com.reelbreaker.features.blocking

import android.accessibilityservice.AccessibilityService
import android.content.Intent
import com.reelbreaker.ui.MindfulTileActivity

class BlockController(private val service: AccessibilityService, private val sessionManager: SessionManager) {
    fun handleDetectedReels(): Boolean {
        service.performGlobalAction(AccessibilityService.GLOBAL_ACTION_BACK)
        if (sessionManager.isAllowed() || sessionManager.isSnoozed()) return false
        service.startActivity(Intent(service, MindfulTileActivity::class.java).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK))
        return true
    }
}
