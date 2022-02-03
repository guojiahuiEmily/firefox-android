/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package org.mozilla.fenix.crashes

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.annotation.VisibleForTesting
import androidx.constraintlayout.widget.ConstraintLayout
import org.mozilla.fenix.R
import org.mozilla.fenix.databinding.FragmentCrashReporterBinding
import org.mozilla.fenix.ext.increaseTapArea

/**
 * Fragment shown when a tab crashes.
 */
class CrashReporterFragment @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {
    @VisibleForTesting
    internal lateinit var binding: FragmentCrashReporterBinding
    @VisibleForTesting val isBindingInitialized
        get() = ::binding.isInitialized
    @VisibleForTesting
    internal lateinit var controller: CrashReporterController

    /**
     * Inflate if necessary and show this `View`.
     *
     * @param controller [CrashReporterController] delegated for all user interactions with this `View`.
     */
    fun show(controller: CrashReporterController) {
        this.controller = controller
        inflateViewIfNecessary()
        visibility = VISIBLE
    }

    /**
     * Remove this View from layout.
     */
    fun hide() {
        visibility = GONE
    }

    @VisibleForTesting
    internal fun inflateViewIfNecessary() {
        if (isBindingInitialized) {
            return
        }

        inflate()
        bindViews()
    }

    @VisibleForTesting
    internal fun inflate() {
        binding = FragmentCrashReporterBinding.inflate(LayoutInflater.from(context), this, true)
    }

    @VisibleForTesting
    internal fun bindViews() {
        binding.title.text =
            context.getString(R.string.tab_crash_title_2, context.getString(R.string.app_name))

        binding.restoreTabButton.apply {
            increaseTapArea(TAP_INCREASE_DP)
            setOnClickListener {
                controller.handleCloseAndRestore(binding.sendCrashCheckbox.isChecked)
            }
        }

        binding.closeTabButton.apply {
            increaseTapArea(TAP_INCREASE_DP)
            setOnClickListener {
                controller.handleCloseAndRemove(binding.sendCrashCheckbox.isChecked)
            }
        }
    }

    companion object {
        @VisibleForTesting
        internal const val TAP_INCREASE_DP = 12
    }
}
