package com.joohnq.moodapp.view.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.joohnq.moodapp.view.constants.Colors
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun MindfulTrackerCardRow(
    icon: DrawableResource,
    color: Color,
    backgroundColor: Color,
    title: StringResource,
    subtitle: StringResource,
    content: @Composable (modifier: Modifier) -> Unit
) {
    val shape = RoundedCornerShape(24.dp)
    Row(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)
            .background(color = Colors.White, shape = shape).padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier.background(
                    color = backgroundColor,
                    shape = shape
                ).size(64.dp),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(icon),
                    contentDescription = stringResource(title),
                    modifier = Modifier.size(24.dp),
                    tint = color
                )
            }
            Spacer(modifier = Modifier.width(10.dp))
            Column(modifier = Modifier) {
                Text(text = stringResource(title), style = TextStyles.MindfulTrackerCardTitle())
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    text = stringResource(subtitle),
                    style = TextStyles.MindfulTrackerCardSubtitle()
                )
            }
        }
        Box(modifier = Modifier) {
            content(Modifier)
        }
    }
}

@Composable
fun MindfulTrackerCardColumn(
    icon: DrawableResource,
    color: Color,
    backgroundColor: Color,
    title: StringResource,
    subtitle: StringResource,
    content: @Composable () -> Unit
) {
    val shape = RoundedCornerShape(24.dp)
    Row(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)
            .background(color = Colors.White, shape = shape).padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Box(
            modifier = Modifier.background(
                color = backgroundColor,
                shape = shape
            ).size(64.dp),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(icon),
                contentDescription = stringResource(title),
                modifier = Modifier.size(24.dp),
                tint = color
            )
        }
        Spacer(modifier = Modifier.width(10.dp))
        Column {
            Text(text = stringResource(title), style = TextStyles.MindfulTrackerCardTitle())
            Spacer(modifier = Modifier.height(8.dp))
            Box(modifier = Modifier.fillMaxWidth()) {
                content()
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = stringResource(subtitle), style = TextStyles.MindfulTrackerCardSubtitle())
        }
    }
}