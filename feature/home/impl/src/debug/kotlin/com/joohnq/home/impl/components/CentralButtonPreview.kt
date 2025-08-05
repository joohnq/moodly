package com.joohnq.home.impl.components

import androidx.compose.runtime.Composable
import com.joohnq.home.impl.ui.components.CentralButton
import com.joohnq.navigation.Destination
import com.joohnq.shared_resources.theme.Drawables
import com.joohnq.ui.entity.CentralAction
import org.jetbrains.compose.ui.tooling.preview.Preview

@Preview
@Composable
private fun Preview() {
    CentralButton(
        item =
            CentralAction(
                title = "Title",
                icon = Drawables.Icons.Outlined.Sleep,
                destination = Destination.App.DashBoard.Home
            )
    )
}
