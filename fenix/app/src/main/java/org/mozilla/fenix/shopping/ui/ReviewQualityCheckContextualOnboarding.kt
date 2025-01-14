/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package org.mozilla.fenix.shopping.ui

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import org.mozilla.fenix.R
import org.mozilla.fenix.compose.button.PrimaryButton
import org.mozilla.fenix.compose.button.TextButton
import org.mozilla.fenix.theme.FirefoxTheme

/**
 * A placeholder UI for review quality check contextual onboarding. The actual UI will be
 * implemented as part of Bug 1840103 with the illustration.
 *
 * @param onPrimaryButtonClick Invoked when a user clicks on the primary button.
 * @param onSecondaryButtonClick Invoked when a user clicks on the secondary button.
 */
@Composable
fun ColumnScope.ReviewQualityCheckContextualOnboarding(
    onPrimaryButtonClick: () -> Unit,
    onSecondaryButtonClick: () -> Unit,
) {
    ReviewQualityCheckCard(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = stringResource(R.string.review_quality_check_contextual_onboarding_title),
            color = FirefoxTheme.colors.textPrimary,
            style = FirefoxTheme.typography.headline5,
            textAlign = TextAlign.Center,
            modifier = Modifier.align(Alignment.CenterHorizontally),
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = createDescriptionString(),
            color = FirefoxTheme.colors.textPrimary,
            style = FirefoxTheme.typography.body2,
            textAlign = TextAlign.Center,
            modifier = Modifier.align(Alignment.CenterHorizontally),
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = stringResource(
                id = R.string.review_quality_check_contextual_onboarding_learn_more,
                stringResource(id = R.string.shopping_product_name),
                stringResource(id = R.string.review_quality_check_contextual_onboarding_learn_more_link),
            ),
            color = FirefoxTheme.colors.textPrimary,
            style = FirefoxTheme.typography.body2,
            textAlign = TextAlign.Center,
            modifier = Modifier.align(Alignment.CenterHorizontally),
        )

        Spacer(modifier = Modifier.height(16.dp))

        PrimaryButton(
            text = stringResource(R.string.review_quality_check_contextual_onboarding_primary_button_text),
            onClick = onPrimaryButtonClick,
        )

        Spacer(modifier = Modifier.height(8.dp))

        TextButton(
            text = stringResource(R.string.review_quality_check_contextual_onboarding_secondary_button_text),
            onClick = onSecondaryButtonClick,
            modifier = Modifier.fillMaxWidth(),
        )
    }

    Spacer(modifier = Modifier.height(8.dp))

    Text(
        text = stringResource(
            R.string.review_quality_check_contextual_onboarding_caption,
            stringResource(R.string.shopping_product_name),
            stringResource(id = R.string.review_quality_check_contextual_onboarding_privacy_policy),
            stringResource(id = R.string.review_quality_check_contextual_onboarding_terms_use),
        ),
        color = FirefoxTheme.colors.textPrimary,
        style = FirefoxTheme.typography.caption,
        textAlign = TextAlign.Center,
        modifier = Modifier.align(Alignment.CenterHorizontally),
    )
}

@Composable
private fun createDescriptionString(
    retailers: List<Int> = listOf(
        R.string.review_quality_check_retailer_name_amazon,
        R.string.review_quality_check_retailer_name_bestbuy,
        R.string.review_quality_check_retailer_name_walmart,
    ),
) = buildAnnotatedString {
    val description = stringResource(
        id = R.string.review_quality_check_contextual_onboarding_description,
        stringResource(retailers[0]),
        stringResource(R.string.app_name),
        stringResource(retailers[1]),
        stringResource(retailers[2]),
    )
    append(description)

    retailers.forEach {
        val retailer = stringResource(id = it)
        val start = description.indexOf(retailer)

        addStyle(
            style = SpanStyle(fontWeight = FontWeight.Bold),
            start = start,
            end = start + retailer.length,
        )
    }
}
