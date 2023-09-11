package com.shakebugs.demo.ui

import android.os.Bundle
import android.util.Log
import android.widget.SeekBar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SwitchCompat
import com.shakebugs.demo.R
import com.shakebugs.demo.databinding.SettingsActivityBinding
import com.shakebugs.demo.utils.PreferenceUtils
import com.shakebugs.demo.utils.ShakeUtils
import com.shakebugs.shake.Shake


class SettingsActivity : AppCompatActivity() {

    private lateinit var binding: SettingsActivityBinding

    private lateinit var invokeShaking: SwitchCompat
    private lateinit var shakingThreshold: SeekBar
    private lateinit var invokeScreenshot: SwitchCompat
    private lateinit var invokeButton: SwitchCompat

    private lateinit var feedbackType: SwitchCompat
    private lateinit var emailField: SwitchCompat
    private lateinit var inspectButton: SwitchCompat

    private lateinit var screenshot: SwitchCompat

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.settings_activity)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
            setDisplayShowTitleEnabled(false)
            setBackgroundDrawable(applicationContext.getDrawable(R.color.shake_color_primary))
        }

        binding = SettingsActivityBinding.inflate(layoutInflater)
        binding.root.setBackgroundColor(applicationContext.getColor(R.color.shake_color_primary))
        setContentView(binding.root)

        Shake.addPrivateView(this)
        val preferenceUtils = PreferenceUtils()

        invokeShaking = binding.settingsShaking
        shakingThreshold = binding.settingsShakingThreshold
        invokeScreenshot = binding.settingsScreenshot
        invokeButton = binding.settingsButton
        invokeShaking.isChecked = Shake.getReportConfiguration().isInvokeShakeOnShakeDeviceEvent
        invokeShaking.setOnCheckedChangeListener { _, isChecked ->
            Log.d("Settings", "Shake invocation: $isChecked")
            Shake.getReportConfiguration().isInvokeShakeOnShakeDeviceEvent = isChecked
            preferenceUtils.saveBoolean(this, PreferenceUtils.IS_INVOKED_BY_SHAKING, isChecked)
        }
        shakingThreshold.progress = Shake.getReportConfiguration().shakingThreshold
        shakingThreshold.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {}

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                Log.d("Settings", "Shake sensitivity is set to: ${seekBar!!.progress}")
                Shake.getReportConfiguration().shakingThreshold = seekBar.progress
                preferenceUtils.saveInt(
                    applicationContext,
                    PreferenceUtils.SHAKING_THRESHOLD,
                    seekBar.progress
                )
            }

        })
        invokeScreenshot.isChecked = Shake.getReportConfiguration().isInvokeShakeOnScreenshot
        invokeScreenshot.setOnCheckedChangeListener { _, isChecked ->
            Log.d("Settings", "Screenshot invocation: $isChecked")
            Shake.getReportConfiguration().isInvokeShakeOnScreenshot = isChecked
            preferenceUtils.saveBoolean(this, PreferenceUtils.IS_INVOKED_ON_SCREENSHOT, isChecked)
        }
        invokeButton.isChecked = Shake.getReportConfiguration().isShowFloatingReportButton
        invokeButton.setOnCheckedChangeListener { _, isChecked ->
            Log.d("Settings", "Button invocation: $isChecked")
            Shake.getReportConfiguration().isShowFloatingReportButton = isChecked
            preferenceUtils.saveBoolean(this, PreferenceUtils.IS_FLOATING_BUTTON_SHOWN, isChecked)
        }

        feedbackType = binding.settingsFeedbackType
        emailField = binding.settingsEmail
        inspectButton = binding.settingsInspect

        feedbackType.isChecked =
            preferenceUtils.getBoolean(this, PreferenceUtils.IS_FEEDBACK_TYPE_ENABLED)
        feedbackType.setOnCheckedChangeListener { _, isChecked ->
            Log.d("Settings", "Feedback types: $isChecked")
            preferenceUtils.saveBoolean(this, PreferenceUtils.IS_FEEDBACK_TYPE_ENABLED, isChecked)
            ShakeUtils.buildShakeForm(this, preferenceUtils)
        }
        emailField.isChecked =
            preferenceUtils.getBoolean(this, PreferenceUtils.IS_EMAIL_FIELD_ENABLED)
        emailField.setOnCheckedChangeListener { _, isChecked ->
            Log.d("Settings", "Email field: $isChecked")
            preferenceUtils.saveBoolean(this, PreferenceUtils.IS_EMAIL_FIELD_ENABLED, isChecked)
            ShakeUtils.buildShakeForm(this, preferenceUtils)
        }
        inspectButton.isChecked =
            preferenceUtils.getBoolean(this, PreferenceUtils.IS_INSPECT_SCREEN_ENABLED)
        inspectButton.setOnCheckedChangeListener { _, isChecked ->
            Log.d("Settings", "Inspect button: $isChecked")
            preferenceUtils.saveBoolean(this, PreferenceUtils.IS_INSPECT_SCREEN_ENABLED, isChecked)
            ShakeUtils.buildShakeForm(this, preferenceUtils)
        }

        screenshot = binding.settingsAttachment
        screenshot.isChecked = Shake.getReportConfiguration().isScreenshotIncluded
        screenshot.setOnCheckedChangeListener { _, isChecked ->
            Log.d("Settings", "Screenshot included: $isChecked")
            Shake.getReportConfiguration().isScreenshotIncluded = isChecked
            preferenceUtils.saveBoolean(this, PreferenceUtils.IS_SCREENSHOT_INCLUDED, isChecked)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun finish() {
        super.finish()
        overridePendingTransition(R.anim.slide_in_from_left, R.anim.slide_out_to_right)
    }

}
