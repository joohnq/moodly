package com.joohnq.home.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.joohnq.core.ui.entity.DIcon
import com.joohnq.home.ui.BottomItem
import com.joohnq.home.ui.presentation.dashboard.createTabItem
import com.joohnq.navigation.Destination
import com.joohnq.shared_resources.Res
import com.joohnq.shared_resources.components.BottomNavigationActionButton
import com.joohnq.shared_resources.home
import com.joohnq.shared_resources.journaling
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.Dimens
import com.joohnq.shared_resources.theme.Drawables
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun DashboardBottomNavigation(
    isCurrentRoute: (Destination) -> Boolean,
    onNavigate: (Destination) -> Unit = {},
    isExpanded: Boolean,
    toggleIsExpanded: () -> Unit = {},
) {
    val bottomItems = remember {
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
                    icon = Drawables.Icons.Document,
                    modifier = Modifier.size(Dimens.Icon),
                    contentDescription = Res.string.journaling
                ),
                title = Res.string.journaling,
                route = Destination.App.DashBoard.Journaling,
            ),
        )
    }
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Colors.White)
            .padding(horizontal = 20.dp)
            .padding(vertical = 10.dp),
    ) {
        bottomItems[0].createTabItem(
            isCurrentRoute = isCurrentRoute,
            onNavigate = onNavigate
        )
        BottomNavigationActionButton(
            modifier = Modifier.size(56.dp),
            onClick = toggleIsExpanded,
            image = if(isExpanded) Drawables.Icons.Close else Drawables.Icons.Logo,
        )
        bottomItems[1].createTabItem(
            isCurrentRoute = isCurrentRoute,
            onNavigate = onNavigate
        )
    }
}

@Preview
@Composable
fun DashboardBottomNavigationPreview() {
    DashboardBottomNavigation(
        isCurrentRoute = { true },
        isExpanded = false,
    )
}