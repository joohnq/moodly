package com.joohnq.moodapp.entities

import com.joohnq.moodapp.view.constants.Drawables
import com.joohnq.moodapp.view.screens.Screens
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
    data object Add : BottomScreens<Screens.AddMoodScreen>(
        name = "Add",
        icon = Drawables.Icons.Add,
        route = Screens.AddMoodScreen
    )

    @Serializable
    data object Journaling : BottomScreens<Screens.HomeGraph.JournalingScreen>(
        name = "Journaling",
        icon = Drawables.Icons.Chat,
        route = Screens.HomeGraph.JournalingScreen
    )
}