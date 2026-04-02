package com.reelbreaker.features.unlock

import android.content.Context
import android.widget.Toast

class StrictModeManager {
    fun showProtectionNudge(context: Context) {
        Toast.makeText(context, "Strict Mode active: take one mindful step first.", Toast.LENGTH_SHORT).show()
    }
}
