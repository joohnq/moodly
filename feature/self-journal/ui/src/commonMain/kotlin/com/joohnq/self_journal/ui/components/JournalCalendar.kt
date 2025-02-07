package com.joohnq.self_journal.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.joohnq.self_journal.domain.entity.SelfJournalRecord
import com.joohnq.self_journal.domain.use_case.GetSelfJournalsInYearUseCase
import com.joohnq.shared_resources.components.GiganticCreateCard
import org.koin.compose.koinInject

@Composable
fun JournalCalendar(
    modifier: Modifier = Modifier,
    containerColor: Color,
    records: List<SelfJournalRecord>,
    subtitle: String,
    onCreate: () -> Unit,
    onClick: () -> Unit
) {
    val useCase: GetSelfJournalsInYearUseCase = koinInject()
    val recordsInYear = useCase.invoke(selfJournals = records)

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