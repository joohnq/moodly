package com.joohnq.gratefulness.overview.presentation.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.Dimens
import com.joohnq.shared_resources.theme.TextStyles
import com.kizitonwose.calendar.core.WeekDay
import kotlinx.datetime.LocalDate

@Composable
fun GratefulnessOverviewPanelDay(
    hasItem: Boolean,
    selectedDate: LocalDate,
    day: WeekDay,
    onClick: () -> Unit,
) {
    Card(
        modifier =
            Modifier
                .width(32.dp)
                .height(52.dp)
                .fillMaxHeight(),
        onClick = onClick,
        elevation =
            CardDefaults.cardElevation(
                defaultElevation = 0.dp
            ),
        colors =
            CardColors(
                containerColor = if (day.date == selectedDate) Colors.Green5 else Colors.White,
                contentColor = Color.Unspecified,
                disabledContainerColor = Color.Unspecified,
                disabledContentColor = Color.Unspecified
            ),
        shape = Dimens.Shape.Circle,
        border =
            BorderStroke(
                width = 1.dp,
                color = if (day.date == selectedDate) Colors.Green40 else Colors.Gray30
            )
    ) {
        Column(
            modifier =
                Modifier
                    .fillMaxSize()
                    .padding(vertical = 5.dp, horizontal = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text =
                    day.date.dayOfWeek.name
                        .first()
                        .toString(),
                style = TextStyles.textXsRegular(),
                color = Colors.Gray60
            )
            Text(
                text = day.date.day.toString(),
                style = TextStyles.textSmSemiBold(),
                color = Colors.Gray80
            )
            if (hasItem) {
                Box(
                    modifier =
                        Modifier
                            .size(6.dp)
                            .clip(Dimens.Shape.Circle)
                            .background(Colors.Green40, shape = Dimens.Shape.Circle)
                )
            }
        }
    }
}
