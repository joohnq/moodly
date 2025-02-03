package com.joohnq.health_journal.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import com.joohnq.health_journal.domain.entity.HealthJournalRecord
import com.joohnq.shared_resources.Res
import com.joohnq.shared_resources.empty
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.Drawables
import com.joohnq.shared_resources.theme.PaddingModifier.Companion.paddingHorizontalMedium
import com.joohnq.shared_resources.theme.TextStyles
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun SelfJournalsItems(
    items: List<HealthJournalRecord>?,
    onClick: (Int) -> Unit,
    onDelete: (Int) -> Unit,
) {
    if (items.isNullOrEmpty())
        Column(
            modifier = Modifier.fillMaxWidth().paddingHorizontalMedium(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(Drawables.Images.NotFound),
                contentDescription = stringResource(Res.string.empty),
                modifier = Modifier.fillMaxWidth(),
                contentScale = ContentScale.FillWidth
            )
            Text(
                text = stringResource(Res.string.empty),
                style = TextStyles.Text2xlExtraBold(),
                color = Colors.Brown80
            )
        }
    else
        LazyColumn(modifier = Modifier.fillMaxWidth()) {
            itemsIndexed(items) { i, healthJournal ->
                SelfJournalCard(
                    isNotFirst = i != 0,
                    isNotLast = i != items.lastIndex,
                    record = healthJournal,
                    onClick = onClick,
                    onDelete = {
                        onDelete(healthJournal.id)
                    }
                )
            }
        }
}