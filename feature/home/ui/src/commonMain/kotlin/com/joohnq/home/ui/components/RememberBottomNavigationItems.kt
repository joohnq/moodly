package com.joohnq.home.ui.components

import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.joohnq.domain.entity.BottomItem
import com.joohnq.domain.entity.DIcon
import com.joohnq.navigation.Destination
import com.joohnq.shared_resources.Res
import com.joohnq.shared_resources.home
import com.joohnq.shared_resources.theme.Dimens
import com.joohnq.shared_resources.theme.Drawables

@Composable
fun rememberBottomNavigationItems(): List<BottomItem<Destination>> = remember {
    listOf(
        BottomItem(
            icon = DIcon(
                icon = Drawables.Icons.Outlined.Home,
                modifier = Modifier.size(Dimens.Icon),
                contentDescription = Res.string.home
            ),
            title = Res.string.home,
            route = Destination.App.DashBoard.Home
        ),
    )
}