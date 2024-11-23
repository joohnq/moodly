package com.joohnq.moodapp.domain

import cafe.adriel.voyager.core.screen.Screen
import com.joohnq.moodapp.ui.presentation.add_stats.AddStatScreen
import com.joohnq.moodapp.ui.presentation.home.HomeScreen
import com.joohnq.moodapp.ui.presentation.journaling.JournalingScreen
import com.joohnq.moodapp.ui.theme.Drawables
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import moodapp.composeapp.generated.resources.Res
import moodapp.composeapp.generated.resources.add
import moodapp.composeapp.generated.resources.home
import moodapp.composeapp.generated.resources.journaling
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource

@Serializable
sealed class BottomScreens(
    @Contextual val name: StringResource,
    @Contextual val icon: DrawableResource,
    val route: Screen
) {
    @Serializable
    data object Home : BottomScreens(
        name = Res.string.home,
        icon = Drawables.Icons.Home,
        route = HomeScreen()
    )

    @Serializable
    data object Add : BottomScreens(
        name = Res.string.add,
        icon = Drawables.Icons.Add,
        route = AddStatScreen()
    )

    @Serializable
    data object Journaling : BottomScreens(
        name = Res.string.journaling,
        icon = Drawables.Icons.Chat,
        route = JournalingScreen()
    )
}