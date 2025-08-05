package com.joohnq.shared_resources.components.button

import androidx.compose.runtime.Composable
import com.joohnq.shared_resources.Res
import com.joohnq.shared_resources.app_name
import com.joohnq.shared_resources.theme.Drawables
import org.jetbrains.compose.ui.tooling.preview.Preview

@Preview
@Composable
private fun Preview() {
    BottomNavigationButton(
        image = Drawables.Icons.Outlined.Logo,
        description = Res.string.app_name
    )
}
