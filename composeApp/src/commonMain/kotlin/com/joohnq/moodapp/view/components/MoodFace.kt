package com.joohnq.moodapp.view.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.joohnq.moodapp.model.entities.Mood
import org.jetbrains.compose.resources.painterResource

@Composable
fun MoodFace(modifier: Modifier = Modifier, mood: Mood) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .background(color = mood.backgroundColor, shape = CircleShape)
    ) {
        Icon(
            painter = painterResource(mood.image),
            contentDescription = null,
            modifier = modifier,
            tint = mood.color
        )
    }
}

@Composable
fun MoodFaceRoulette(mood: Mood) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.size(150.dp)
            .background(color = mood.backgroundColor)
    ) {
        Icon(
            painter = painterResource(mood.image),
            contentDescription = null,
            modifier = Modifier.size(120.dp),
            tint = mood.color
        )
    }
}