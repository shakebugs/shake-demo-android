package com.shakebugs.demo

import android.app.Application
import com.shakebugs.demo.BuildConfig
import com.shakebugs.shake.Shake

class ShakeDemoApp : Application() {

    override fun onCreate() {
        super.onCreate()

        Shake.setCrashReportingEnabled(true)
        Shake.setAskForCrashDescription(true)

        Shake.getReportConfiguration().isInvokeShakeOnShakeDeviceEvent = true
        Shake.getReportConfiguration().isShowFloatingReportButton = false
        Shake.getReportConfiguration().isInvokeShakeOnScreenshot = false
        Shake.getReportConfiguration().isInvokeShakeOnRightEdgePan = false

        Shake.getReportConfiguration().isConsoleLogsEnabled = false
        Shake.getReportConfiguration().isEnableBlackBox = false

        Shake.getReportConfiguration().isAutoVideoRecording = false
        Shake.getReportConfiguration().isScreenshotIncluded = true

        Shake.getReportConfiguration().isEnableEmailField = false
        Shake.getReportConfiguration().isEnableInspectScreen = false
        Shake.getReportConfiguration().isFeedbackTypeEnabled = false
        Shake.setShowIntroMessage(true)

        Shake.start(this, BuildConfig.CLIENT_ID, BuildConfig.CLIENT_SECRET)

    }
}