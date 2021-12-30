package com.shakebugs.demo.utils

import android.content.Context


class PreferenceUtils {

    companion object {
        private const val PREFS_NAME = "ShakeDemoAppSharedPreferences"

        const val PREF_APP_UUID = "app_uuid"

        const val IS_INVOKED_BY_SHAKING = "isInvokeShakeOnShakeDeviceEvent"
        const val SHAKING_THRESHOLD = "shakingThreshold"
        const val IS_FLOATING_BUTTON_SHOWN = "isShowFloatingReportButton"
        const val IS_INVOKED_ON_SCREENSHOT = "isInvokeShakeOnScreenshot"
        const val IS_INVOKED_ON_RIGHT_EDGE_PAN = "isInvokeShakeOnRightEdgePan"

        const val IS_CONSOLE_LOG_ENABLED = "isConsoleLogsEnabled"
        const val IS_BLACKBOX_ENABLED = "isEnableBlackBox"
        const val IS_SCREENSHOT_INCLUDED = "isScreenshotIncluded"

        const val IS_EMAIL_FIELD_ENABLED = "isEnableEmailField"
        const val IS_INSPECT_SCREEN_ENABLED = "isEnableInspectScreen"
        const val IS_FEEDBACK_TYPE_ENABLED = "isFeedbackTypeEnabled"
    }


    fun saveInt(context: Context, key: String?, value: Int) {
        context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
            .edit()
            .putInt(key, value)
            .apply()
    }

    fun getInt(context: Context, key: String?): Int {
        return context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
            .getInt(key, -1)
    }

    fun saveBoolean(context: Context, key: String?, value: Boolean) {
        context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
            .edit()
            .putBoolean(key, value)
            .apply()
    }

    fun getBoolean(context: Context, key: String?): Boolean {
        return context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
            .getBoolean(key, false)
    }

    fun saveString(context: Context, key: String?, value: String?) {
        context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
            .edit()
            .putString(key, value)
            .apply()
    }

    fun getString(context: Context, key: String?): String? {
        return context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
            .getString(key, "")
    }

}