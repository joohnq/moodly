package com.joohnq.health_journal.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.joohnq.health_journal.domain.entity.HealthJournalRecord
import com.joohnq.health_journal.domain.use_case.GetHealthJournalsInYearUseCase
import com.joohnq.shared_resources.components.GiganticCreateCard
import kotlinx.datetime.LocalDate
import org.koin.compose.koinInject

@Composable
fun JournalCalendar(
    modifier: Modifier = Modifier,
    containerColor: Color,
    records: List<HealthJournalRecord>,
    subtitle: String,
    onCreate: () -> Unit,
    onClick: () -> Unit
) {
    val useCase: GetHealthJournalsInYearUseCase = koinInject()
    val recordsInYear = useCase.invoke(healthJournals = records)

    GiganticCreateCard(
        modifier = modifier,
        containerColor = containerColor,
        title = recordsInYear,
        subtitle = subtitle,
        onCreate = onCreate,
        onClick = onClick,
        content = {
            SelfJournalingCalendar(
                records = records,
            )
        }
    )
}