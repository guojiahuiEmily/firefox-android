/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package org.mozilla.fenix.shopping

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.rememberNestedScrollInteropConnection
import androidx.lifecycle.lifecycleScope
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import org.mozilla.fenix.BrowserDirection
import org.mozilla.fenix.HomeActivity
import org.mozilla.fenix.ext.requireComponents
import org.mozilla.fenix.shopping.di.ReviewQualityCheckMiddlewareProvider
import org.mozilla.fenix.shopping.store.ReviewQualityCheckStore
import org.mozilla.fenix.shopping.ui.ReviewQualityCheckBottomSheet
import org.mozilla.fenix.theme.FirefoxTheme

/**
 * A bottom sheet fragment displaying Review Quality Check information.
 */
class ReviewQualityCheckFragment : BottomSheetDialogFragment() {

    private var behavior: BottomSheetBehavior<View>? = null
    private val store by lazy {
        ReviewQualityCheckStore(
            middleware = ReviewQualityCheckMiddlewareProvider.provideMiddleware(
                settings = requireComponents.settings,
                browserStore = requireComponents.core.store,
                context = requireContext().applicationContext,
                openLink = { link, shouldOpenInNewTab ->
                    (requireActivity() as HomeActivity).openToBrowserAndLoad(
                        searchTermOrURL = link,
                        newTab = shouldOpenInNewTab,
                        from = BrowserDirection.FromReviewQualityCheck,
                    )
                },
                scope = lifecycleScope,
            ),
        )
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog =
        super.onCreateDialog(savedInstanceState).apply {
            setOnShowListener {
                val bottomSheet =
                    findViewById<View?>(com.google.android.material.R.id.design_bottom_sheet)
                bottomSheet?.setBackgroundResource(android.R.color.transparent)
                behavior = BottomSheetBehavior.from(bottomSheet)
            }
        }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View = ComposeView(requireContext()).apply {
        setContent {
            FirefoxTheme {
                ReviewQualityCheckBottomSheet(
                    store = store,
                    onRequestDismiss = {
                        behavior?.state = BottomSheetBehavior.STATE_HIDDEN
                    },
                    modifier = Modifier.nestedScroll(rememberNestedScrollInteropConnection()),
                )
            }
        }
    }
}
