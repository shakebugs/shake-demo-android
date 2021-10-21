package com.shakebugs.demo.ui

import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SwitchCompat
import com.shakebugs.demo.R
import com.shakebugs.demo.databinding.SettingsActivityBinding
import com.shakebugs.shake.Shake

import android.widget.CompoundButton




class SettingsActivity : AppCompatActivity() {

    private lateinit var binding: SettingsActivityBinding

    lateinit var invokeShaking : SwitchCompat
    lateinit var invokeScreenshot : SwitchCompat
    lateinit var invokeButton : SwitchCompat

    lateinit var feedbackType : SwitchCompat
    lateinit var emailField : SwitchCompat
    lateinit var inspectButton : SwitchCompat

    lateinit var screenshot : SwitchCompat

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.settings_activity)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
            setDisplayShowTitleEnabled(false)
            setBackgroundDrawable(resources.getDrawable(R.color.shake_color_primary))
        }

        binding = SettingsActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Shake.addPrivateView(this)

        invokeShaking = binding.settingsShaking
        invokeScreenshot = binding.settingsScreenshot
        invokeButton = binding.settingsButton
        invokeShaking.isChecked = Shake.getReportConfiguration().isInvokeShakeOnShakeDeviceEvent
        invokeShaking.setOnCheckedChangeListener { _, isChecked ->
            Log.d("Settings", "Shake invocation: $isChecked")
            Shake.getReportConfiguration().isInvokeShakeOnShakeDeviceEvent = isChecked
        }
        invokeScreenshot.isChecked = Shake.getReportConfiguration().isInvokeShakeOnScreenshot
        invokeScreenshot.setOnCheckedChangeListener { _, isChecked ->
            Log.d("Settings", "Screenshoot invocation: $isChecked")
            Shake.getReportConfiguration().isInvokeShakeOnScreenshot = isChecked
        }
        invokeButton.isChecked = Shake.getReportConfiguration().isShowFloatingReportButton
        invokeButton.setOnCheckedChangeListener { _, isChecked ->
            Log.d("Settings", "Button invocation: $isChecked")
            Shake.getReportConfiguration().isShowFloatingReportButton = isChecked
        }

        feedbackType = binding.settingsFeedbackType
        emailField = binding.settingsEmail
        inspectButton = binding.settingsInspect
        feedbackType.isChecked = Shake.getReportConfiguration().isFeedbackTypeEnabled
        feedbackType.setOnCheckedChangeListener { _, isChecked ->
            Log.d("Settings", "Feedback types: $isChecked")
            Shake.getReportConfiguration().isFeedbackTypeEnabled = isChecked
        }
        emailField.isChecked = Shake.getReportConfiguration().isEnableEmailField
        emailField.setOnCheckedChangeListener { _, isChecked ->
            Log.d("Settings", "Email field: $isChecked")
            Shake.getReportConfiguration().isEnableEmailField = isChecked
        }
        inspectButton.isChecked = Shake.getReportConfiguration().isEnableInspectScreen
        inspectButton.setOnCheckedChangeListener { _, isChecked ->
            Log.d("Settings", "Inspect button: $isChecked")
            Shake.getReportConfiguration().isEnableInspectScreen = isChecked
        }

        screenshot = binding.settingsAttachment
        screenshot.isChecked = Shake.getReportConfiguration().isScreenshotIncluded
        screenshot.setOnCheckedChangeListener { _, isChecked ->
            Log.d("Settings", "Screenshoot included: $isChecked")
            Shake.getReportConfiguration().isScreenshotIncluded = isChecked
        }

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        overridePendingTransition(R.anim.slide_in_from_left, R.anim.slide_out_to_right)
        return true
    }


    override fun dispatchTouchEvent(event: MotionEvent): Boolean {
        Shake.handleTouchEvent(event, this)
        return super.dispatchTouchEvent(event)
    }

}
