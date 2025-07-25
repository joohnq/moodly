package com.joohnq.shared_resources.components.text

import androidx.compose.runtime.Composable
import com.joohnq.shared_resources.Res
import com.joohnq.shared_resources.app_name
import org.jetbrains.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun SectionHeaderPreview() {
    SectionHeader(
        title = Res.string.app_name
    )
}