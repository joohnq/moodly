package com.joohnq.home.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.joohnq.navigation.Destination
import com.joohnq.shared_resources.components.BottomNavigationActionButton
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.Drawables
import com.joohnq.shared_resources.theme.PaddingModifier.Companion.paddingHorizontalMedium
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun DashboardBottomNavigation(
    padding: PaddingValues = PaddingValues(bottom = 30.dp),
    isCurrentRoute: (Destination) -> Boolean,
    onNavigate: (Destination) -> Unit = {},
    isCentralExpanded: Boolean,
    toggleIsCentralExpanded: () -> Unit = {},
) {
    val bottomItems = rememberBottomNavigationItems()

    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Colors.White)
            .padding(horizontal = 20.dp)
            .padding(vertical = 10.dp)
            .padding(bottom = padding.calculateBottomPadding()),
    ) {
        CreateTabItem(
            item = bottomItems[0],
            isCurrentRoute = isCurrentRoute,
            onNavigate = onNavigate
        )
        BottomNavigationActionButton(
            modifier = Modifier.size(48.dp),
            onClick = toggleIsCentralExpanded,
            image = if (isCentralExpanded) Drawables.Icons.Close else Drawables.Icons.Logo,
        )
        CreateTabItem(
            item = bottomItems[1],
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
        isCentralExpanded = false,
    )
}