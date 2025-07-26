package com.joohnq.home.impl.ui.components

import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.joohnq.navigation.Destination
import com.joohnq.shared_resources.Res
import com.joohnq.shared_resources.home
import com.joohnq.shared_resources.theme.Dimens
import com.joohnq.shared_resources.theme.Drawables
import com.joohnq.ui.entity.BottomItem
import com.joohnq.ui.entity.IconResource

@Composable
fun rememberBottomNavigationItems(): List<BottomItem<Destination>> = remember {
    listOf(
        BottomItem(
            icon = IconResource(
                icon = Drawables.Icons.Outlined.Home,
                modifier = Modifier.size(Dimens.Icon),
                contentDescription = Res.string.home
            ),
            title = Res.string.home,
            route = Destination.App.DashBoard.Home
        )
    )
}