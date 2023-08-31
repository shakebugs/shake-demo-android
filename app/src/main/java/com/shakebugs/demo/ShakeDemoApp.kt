package com.shakebugs.demo

import android.app.Application
import com.shakebugs.demo.utils.PreferenceUtils
import com.shakebugs.demo.utils.ShakeUtils
import com.shakebugs.shake.Shake
import java.util.*

class ShakeDemoApp : Application() {

    override fun onCreate() {
        super.onCreate()

        val preferenceUtils = PreferenceUtils()

        if (preferenceUtils.getString(this, PreferenceUtils.PREF_APP_UUID).equals("")) {
            preferenceUtils.saveString(this, PreferenceUtils.PREF_APP_UUID, UUID.randomUUID().toString())

            preferenceUtils.saveBoolean(this, PreferenceUtils.IS_INVOKED_BY_SHAKING, true)
            preferenceUtils.saveInt(this, PreferenceUtils.SHAKING_THRESHOLD, Shake.getReportConfiguration().shakingThreshold)
            preferenceUtils.saveBoolean(this, PreferenceUtils.IS_FLOATING_BUTTON_SHOWN, false)
            preferenceUtils.saveBoolean(this, PreferenceUtils.IS_INVOKED_ON_SCREENSHOT, false)
            preferenceUtils.saveBoolean(this, PreferenceUtils.IS_INVOKED_ON_RIGHT_EDGE_PAN, false)

            preferenceUtils.saveBoolean(this, PreferenceUtils.IS_CONSOLE_LOG_ENABLED, false)
            preferenceUtils.saveBoolean(this, PreferenceUtils.IS_BLACKBOX_ENABLED, true)

            preferenceUtils.saveBoolean(this, PreferenceUtils.IS_SCREENSHOT_INCLUDED, true)
            preferenceUtils.saveBoolean(this, PreferenceUtils.IS_EMAIL_FIELD_ENABLED, false)
            preferenceUtils.saveBoolean(this, PreferenceUtils.IS_INSPECT_SCREEN_ENABLED, false)
            preferenceUtils.saveBoolean(this, PreferenceUtils.IS_FEEDBACK_TYPE_ENABLED, false)
        }

        Shake.setCrashReportingEnabled(true)
        Shake.setAskForCrashDescription(true)

        Shake.getReportConfiguration().isInvokeShakeOnShakeDeviceEvent = preferenceUtils.getBoolean(this, PreferenceUtils.IS_INVOKED_BY_SHAKING)
        Shake.getReportConfiguration().shakingThreshold = preferenceUtils.getInt(this, PreferenceUtils.SHAKING_THRESHOLD)
        Shake.getReportConfiguration().isShowFloatingReportButton = preferenceUtils.getBoolean(this, PreferenceUtils.IS_FLOATING_BUTTON_SHOWN)
        Shake.getReportConfiguration().isInvokeShakeOnScreenshot = preferenceUtils.getBoolean(this, PreferenceUtils.IS_INVOKED_ON_SCREENSHOT)
        Shake.getReportConfiguration().isInvokeShakeOnRightEdgePan = preferenceUtils.getBoolean(this, PreferenceUtils.IS_INVOKED_ON_RIGHT_EDGE_PAN)

        Shake.getReportConfiguration().isConsoleLogsEnabled = preferenceUtils.getBoolean(this, PreferenceUtils.IS_CONSOLE_LOG_ENABLED)
        Shake.getReportConfiguration().isEnableBlackBox = preferenceUtils.getBoolean(this, PreferenceUtils.IS_BLACKBOX_ENABLED)

        Shake.getReportConfiguration().isAutoVideoRecording = false
        Shake.getReportConfiguration().isScreenshotIncluded = preferenceUtils.getBoolean(this, PreferenceUtils.IS_SCREENSHOT_INCLUDED)

        ShakeUtils.buildShakeForm(this, preferenceUtils)

        Shake.setShowIntroMessage(true)

        Shake.start(this, BuildConfig.CLIENT_ID, BuildConfig.CLIENT_SECRET)

        Shake.registerUser(preferenceUtils.getString(this, PreferenceUtils.PREF_APP_UUID))
        Shake.updateUserMetadata(mapOf(
            "first_name" to "John",
            "last_name" to "Doe",
            "email" to "john.doe@email.com",
            "status" to "Active",
            "cartItems" to "3",
            "cartTotal" to "$25.33"
        ))

    }
}