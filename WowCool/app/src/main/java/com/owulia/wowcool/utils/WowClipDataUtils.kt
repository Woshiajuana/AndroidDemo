package com.owulia.wowcool.utils

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context

object WowClipDataUtils {
    fun copyText (context: Context, text: String, label: String = "") {
        val clipboardManager = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clipData = ClipData.newPlainText(label, text)
        clipboardManager.setPrimaryClip(clipData)
    }
}