package com.joohnq.moodapp.view

import com.joohnq.moodapp.view.constants.Drawables
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import org.jetbrains.compose.resources.DrawableResource

@Serializable
sealed class BottomScreens<T>(
    val name: String,
    @Contextual val icon: DrawableResource,
    val route: T
) {
    @Serializable
    data object Home : BottomScreens<Screens.HomeGraph.HomeScreen>(
        name = "Home",
        icon = Drawables.Icons.Home,
        route = Screens.HomeGraph.HomeScreen
    )

    @Serializable
    data object Add : BottomScreens<Screens.HomeGraph.AddMoodScreen>(
        name = "Add",
        icon = Drawables.Icons.Add,
        route = Screens.HomeGraph.AddMoodScreen
    )

    @Serializable
    data object Journaling : BottomScreens<Screens.HomeGraph.JournalingScreen>(
        name = "Journaling",
        icon = Drawables.Icons.Chat,
        route = Screens.HomeGraph.JournalingScreen
    )
}