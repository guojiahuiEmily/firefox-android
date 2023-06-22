/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package org.mozilla.focus.widget

import android.content.Context
import android.util.AttributeSet
import mozilla.components.service.glean.Glean
import org.mozilla.focus.R
import org.mozilla.focus.settings.LearnMoreSwitchPreference
import org.mozilla.focus.telemetry.TelemetryWrapper
import org.mozilla.focus.utils.SupportUtils
import org.mozilla.telemetry.TelemetryHolder

/**
 * Switch preference for enabling/disabling telemetry
 */
internal class TelemetrySwitchPreference(context: Context, attrs: AttributeSet?) :
    LearnMoreSwitchPreference(context, attrs) {

    init {
        isChecked = TelemetryWrapper.isTelemetryEnabled(context)
    }

    override fun onClick() {
        super.onClick()
        TelemetryHolder.get()
            .configuration.isUploadEnabled = isChecked

        Glean.setUploadEnabled(isChecked)

        if (isChecked) {
            TelemetryWrapper.startSession()
        } else {
            TelemetryWrapper.stopSession()
        }
    }

    override fun getDescription(): String {
        return context.resources.getString(
            R.string.preference_mozilla_telemetry_summary2,
            context.resources.getString(R.string.app_name),
        )
    }

    override fun getLearnMoreUrl(): String {
        return SupportUtils.getSumoURLForTopic(
            SupportUtils.getAppVersion(context),
            SupportUtils.SumoTopic.USAGE_DATA,
        )
    }
}
