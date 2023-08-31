package com.shakebugs.demo.utils

import android.content.Context
import com.shakebugs.demo.utils.PreferenceUtils.Companion.IS_EMAIL_FIELD_ENABLED
import com.shakebugs.demo.utils.PreferenceUtils.Companion.IS_FEEDBACK_TYPE_ENABLED
import com.shakebugs.demo.utils.PreferenceUtils.Companion.IS_INSPECT_SCREEN_ENABLED
import com.shakebugs.shake.Shake
import com.shakebugs.shake.form.ShakeEmail
import com.shakebugs.shake.form.ShakeInspectButton
import com.shakebugs.shake.form.ShakePicker


object ShakeUtils {

    fun buildShakeForm(context: Context, preferenceUtils: PreferenceUtils) {
        Shake.getReportConfiguration().shakeForm = null
        val shakeForm = Shake.getReportConfiguration().shakeForm

        val showFeedbackPicker = preferenceUtils.getBoolean(context, IS_FEEDBACK_TYPE_ENABLED)
        val showEmailField = preferenceUtils.getBoolean(context, IS_EMAIL_FIELD_ENABLED)
        val showInspectButton = preferenceUtils.getBoolean(context, IS_INSPECT_SCREEN_ENABLED)

        if (!showFeedbackPicker) {
            shakeForm.components.remove(shakeForm.components.find { it is ShakePicker })
        }
        if (showEmailField) {
            shakeForm.components.add(2, ShakeEmail("Email", "Email", required = true))
        }
        if (!showInspectButton) {
            shakeForm.components.remove(shakeForm.components.find { it is ShakeInspectButton })
        }
    }
}