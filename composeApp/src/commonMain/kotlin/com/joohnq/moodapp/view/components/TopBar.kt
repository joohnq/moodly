package com.joohnq.moodapp.view.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.unit.dp
import com.joohnq.moodapp.model.entities.User
import com.joohnq.moodapp.view.constants.Colors
import com.joohnq.moodapp.view.constants.Drawables
import moodapp.composeapp.generated.resources.Res
import moodapp.composeapp.generated.resources.assessments
import moodapp.composeapp.generated.resources.page_of
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun OnboardingTopBar(page: Int, onBack: () -> Unit) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth().padding(vertical = 12.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            ButtonWithArrowOpen(onClick = onBack)
            Spacer(modifier = Modifier.width(10.dp))
            Text(
                stringResource(Res.string.assessments),
                style = TextStyles.OnboardingScreenSession()
            )
        }
        TextWithBackground(
            stringResource(Res.string.page_of, page, 7),
            borderColor = Colors.Transparent,
            backgroundColor = Colors.Brown20,
            textColor = Colors.Brown60
        )
    }
}

@Composable
fun HomeTopBar(modifier: Modifier = Modifier, user: User, date: String) {
    val userName = user.name
    Column(
        modifier = Modifier.fillMaxSize().background(
            color = Colors.White, shape = RoundedCornerShape(
                bottomStart = 20.dp,
                bottomEnd = 20.dp
            )
        ).padding(20.dp).then(modifier)
    ) {
        Row {
            Row(
                horizontalArrangement = Arrangement.spacedBy(5.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(Drawables.Icons.Calendar),
                    modifier = Modifier.size(20.dp),
                    tint = Colors.Brown80,
                    contentDescription = null
                )
                Text(date, style = TextStyles.HomeTopBarDate())
            }
        }
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = "Hi, $userName", style = TextStyles.HomeTopBarName()
        )
    }
}