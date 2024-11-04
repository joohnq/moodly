package com.joohnq.moodapp.entities

import com.joohnq.moodapp.view.constants.Drawables
import com.joohnq.moodapp.view.screens.AddMoodScreen
import com.joohnq.moodapp.view.screens.HomeGraph
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
    data object Home : BottomScreens<HomeGraph.HomeScreen>(
        name = "Home",
        icon = Drawables.Icons.Home,
        route = HomeGraph.HomeScreen
    )

    @Serializable
    data object Add : BottomScreens<AddMoodScreen>(
        name = "Add",
        icon = Drawables.Icons.Add,
        route = AddMoodScreen
    )

    @Serializable
    data object Journaling : BottomScreens<HomeGraph.JournalingScreen>(
        name = "Journaling",
        icon = Drawables.Icons.Chat,
        route = HomeGraph.JournalingScreen
    )
}