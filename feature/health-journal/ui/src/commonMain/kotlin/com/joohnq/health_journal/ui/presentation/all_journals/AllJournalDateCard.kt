package com.joohnq.health_journal.ui.presentation.all_journals

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.joohnq.mood.components.VerticalSpacer
import com.joohnq.mood.ui.theme.Colors
import com.joohnq.mood.ui.theme.Dimens
import com.joohnq.mood.ui.theme.TextStyles
import kotlinx.datetime.LocalDate

@Composable
fun AllJournalDateCard(
    isSelected: Boolean,
    date: LocalDate,
    onAllAction: (AllJournalIntent) -> Unit,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.background(
            color = if (isSelected) Colors.White else Colors.Brown70,
            shape = Dimens.Shape.Circle
        ).padding(horizontal = 10.dp, vertical = 15.dp)
            .clickable { onAllAction(AllJournalIntent.UpdateSelectedDateTime(date)) }
//                                .then(
//                                    if (isSelected) Modifier.border(
//                                        3.dp,
//                                        color = Colors.Brown100Alpha64,
//                                        shape = Dimens.Shape.Circle
//                                    ) else Modifier
//                                )
    ) {
        Text(
            text = "daw",
            style = TextStyles.TextXsBold(),
            color = if (isSelected) Colors.Brown80 else Colors.Brown20
        )
        VerticalSpacer(5.dp)
        Text(
            text = date.dayOfMonth.toString(),
            style = TextStyles.TextMdExtraBold(),
            color = if (isSelected) Colors.Brown80 else Colors.Brown20
        )
    }
}