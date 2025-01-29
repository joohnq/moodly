package com.joohnq.home.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.joohnq.health_journal.domain.entity.HealthJournalRecord
import com.joohnq.health_journal.domain.use_case.GetHealthJournalsInYearUseCase
import com.joohnq.home.ui.presentation.home.getTodayHealthJournalRecord
import com.joohnq.shared_resources.Res
import com.joohnq.shared_resources.add_new_journal
import com.joohnq.shared_resources.components.GiganticCreateCard
import com.joohnq.shared_resources.components.NotFoundHorizontal
import com.joohnq.shared_resources.journals_written_in
import com.joohnq.shared_resources.lets_set_up_daily_journaling_and_self_reflection
import com.joohnq.shared_resources.theme.Drawables
import com.joohnq.shared_resources.theme.PaddingModifier.Companion.paddingHorizontalMedium
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.koinInject


@Composable
fun SelfJournalingNotFound(modifier: Modifier = Modifier, onClick: () -> Unit) {
    NotFoundHorizontal(
        modifier = modifier,
        title = Res.string.lets_set_up_daily_journaling_and_self_reflection,
        subtitle = Res.string.add_new_journal,
        image = Drawables.Images.SelfJournalingIllustration,
        onClick = onClick
    )
}

@Composable
fun SelfJournalingMetric(
    records: List<HealthJournalRecord>,
    onCreate: () -> Unit,
    onClick: () -> Unit,
) {
    val resource = records.getTodayHealthJournalRecord()
    val useCase: GetHealthJournalsInYearUseCase = koinInject()
    val recordsInYear = useCase.invoke(healthJournals = records)

    if (resource == null)
        SelfJournalingNotFound(modifier = Modifier.paddingHorizontalMedium(), onClick = onCreate)
    else {
        GiganticCreateCard(
            modifier = Modifier.paddingHorizontalMedium(),
            title = recordsInYear,
            subtitle = stringResource(
                Res.string.journals_written_in,
                resource.createdAt.month.name
            ),
            onCreate = onCreate,
            onClick = onClick,
            content = {

            }
        )
    }

}