package com.joohnq.moodapp.entities

import com.joohnq.moodapp.view.constants.Drawables
import com.joohnq.moodapp.view.screens.Screens
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import moodapp.composeapp.generated.resources.Res
import moodapp.composeapp.generated.resources.add
import moodapp.composeapp.generated.resources.home
import moodapp.composeapp.generated.resources.journaling
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource

@Serializable
sealed class BottomScreens<T>(
    @Contextual val name: StringResource,
    @Contextual val icon: DrawableResource,
    val route: T
) {
    @Serializable
    data object Home : BottomScreens<Screens.HomeGraph.HomeScreen>(
        name = Res.string.home,
        icon = Drawables.Icons.Home,
        route = Screens.HomeGraph.HomeScreen
    )

    @Serializable
    data object Add : BottomScreens<Screens.AddMoodScreen>(
        name = Res.string.add,
        icon = Drawables.Icons.Add,
        route = Screens.AddMoodScreen
    )

    @Serializable
    data object Journaling : BottomScreens<Screens.HomeGraph.JournalingScreen>(
        name = Res.string.journaling,
        icon = Drawables.Icons.Chat,
        route = Screens.HomeGraph.JournalingScreen
    )
}