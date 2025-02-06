package com.joohnq.home.ui.components

import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.joohnq.core.ui.entity.BottomItem
import com.joohnq.core.ui.entity.DIcon
import com.joohnq.navigation.Destination
import com.joohnq.shared_resources.Res
import com.joohnq.shared_resources.home
import com.joohnq.shared_resources.journaling
import com.joohnq.shared_resources.theme.Dimens
import com.joohnq.shared_resources.theme.Drawables

@Composable
fun rememberBottomNavigationItems(): List<BottomItem<Destination>> = remember {
    listOf(
        BottomItem(
            icon = DIcon(
                icon = Drawables.Icons.Home,
                modifier = Modifier.size(Dimens.Icon),
                contentDescription = Res.string.home
            ),
            title = Res.string.home,
            route = Destination.App.DashBoard.Home
        ),
        BottomItem(
            icon = DIcon(
                icon = Drawables.Icons.Outlined.Document,
                modifier = Modifier.size(Dimens.Icon),
                contentDescription = Res.string.journaling
            ),
            title = Res.string.journaling,
            route = Destination.App.DashBoard.Journaling,
        ),
    )
}