package com.joohnq.gratefulness.overview.presentation

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.joohnq.api.getNow
import com.joohnq.api.mapper.LocalDateMapper.toDayOfWeekFullMonthAbbreviatedDayYear
import com.joohnq.gratefulness.api.entity.Gratefulness
import com.joohnq.gratefulness.api.entity.Quote
import com.joohnq.gratefulness.impl.ui.presentation.component.GratefulnessHistoryCard
import com.joohnq.shared_resources.Res
import com.joohnq.shared_resources.add_new_gratitute
import com.joohnq.shared_resources.components.layout.ConvexGroupLazyLayout
import com.joohnq.shared_resources.components.layout.NotFoundHorizontalLayout
import com.joohnq.shared_resources.components.layout.NotFoundVerticalLayout
import com.joohnq.shared_resources.components.spacer.VerticalSpacer
import com.joohnq.shared_resources.components.text.SectionHeader
import com.joohnq.shared_resources.gratefulness
import com.joohnq.shared_resources.gratefulness_insight_action
import com.joohnq.shared_resources.gratefulness_insight_description
import com.joohnq.shared_resources.gratefulness_insight_title
import com.joohnq.shared_resources.gratefulness_overview_other_day_title
import com.joohnq.shared_resources.gratefulness_overview_today_title
import com.joohnq.shared_resources.gratitude_and_affirmations
import com.joohnq.shared_resources.gratitude_history
import com.joohnq.shared_resources.lets_set_up_daily_gratitude_and_affirmations
import com.joohnq.shared_resources.quotes
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.Dimens
import com.joohnq.shared_resources.theme.Drawables
import com.joohnq.shared_resources.theme.PaddingModifier.paddingAllSmall
import com.joohnq.shared_resources.theme.TextStyles
import com.kizitonwose.calendar.compose.WeekCalendar
import com.kizitonwose.calendar.compose.weekcalendar.WeekCalendarState
import com.kizitonwose.calendar.compose.weekcalendar.rememberWeekCalendarState
import com.kizitonwose.calendar.core.WeekDay
import kotlinx.datetime.LocalDate
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun GratefulnessOverviewContent(
    state: GratefulnessOverviewContract.State,
    weekCalendarState: WeekCalendarState = rememberWeekCalendarState(),
    onEvent: (GratefulnessOverviewContract.Event) -> Unit = {},
    onIntent: (GratefulnessOverviewContract.Intent) -> Unit = {},
) {
    when {
        state.isLoading -> Unit
        state.isError != null -> Unit
        else ->
            SuccessView(
                state = state,
                weekCalendarState = weekCalendarState,
                onEvent = onEvent,
                onIntent = onIntent
            )
    }
}

@Composable
private fun SuccessView(
    state: GratefulnessOverviewContract.State,
    weekCalendarState: WeekCalendarState,
    onEvent: (GratefulnessOverviewContract.Event) -> Unit,
    onIntent: (GratefulnessOverviewContract.Intent) -> Unit,
) {
    ConvexGroupLazyLayout(
        containerColor = Colors.White,
        panelBackgroundColor = Colors.Brown5,
        title = Res.string.gratefulness,
        isDark = true,
        onAddButton = { onEvent(GratefulnessOverviewContract.Event.NavigateToAddGratefulness) },
        onGoBack = { onEvent(GratefulnessOverviewContract.Event.GoBack) },
        panel = { modifier ->
            GratefulnessOverviewPanel(
                modifier = modifier,
                weekCalendarState = weekCalendarState,
                items = state.items,
                selectedDate = state.selectedDate,
                iAmGratefulFor = state.selectedGratefulness?.iAmGratefulFor,
                onSelectDate = { date ->
                    {
                        onIntent(
                            GratefulnessOverviewContract.Intent.ChangeSelectedDate(
                                date
                            )
                        )
                    }
                }
            )
        },
        body = { modifier ->
            GratefulnessOverviewBody(
                modifier = modifier,
                items = state.items,
                quote = state.quote,
                insights = state.insights,
                onDelete = { id -> onIntent(GratefulnessOverviewContract.Intent.Delete(id)) },
                onCreate = { onEvent(GratefulnessOverviewContract.Event.NavigateToAddGratefulness) },
                onSeeMore = { onEvent(GratefulnessOverviewContract.Event.NavigateToGratefulnessHistory) }
            )
        }
    )
}

@Composable
fun GratefulnessOverviewPanel(
    modifier: Modifier = Modifier,
    weekCalendarState: WeekCalendarState,
    items: List<Gratefulness>,
    selectedDate: LocalDate,
    iAmGratefulFor: String?,
    onSelectDate: (LocalDate) -> () -> Unit,
) {
    val isToday = selectedDate == getNow().date

    Column {
        WeekCalendar(
            state = weekCalendarState,
            contentPadding = PaddingValues(horizontal = 20.dp),
            dayContent = { day ->
                val hasItem = items.any { it.createdAt.date == day.date }

                GratefulnessOverviewPanelDay(
                    hasItem = hasItem,
                    selectedDate = selectedDate,
                    day = day,
                    onClick = onSelectDate(day.date)
                )
            }
        )

        Column(
            modifier = modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            VerticalSpacer(32.dp)
            Text(
                text = selectedDate.toDayOfWeekFullMonthAbbreviatedDayYear(),
                style = TextStyles.textSmMedium(),
                color = Colors.Gray60,
                textAlign = TextAlign.Center
            )
            VerticalSpacer(16.dp)
            Text(
                text =
                    stringResource(
                        if (isToday) {
                            Res.string.gratefulness_overview_today_title
                        } else {
                            Res.string.gratefulness_overview_other_day_title
                        },
                        iAmGratefulFor ?: "..."
                    ),
                style = TextStyles.headingMdRegular(),
                color = Colors.Brown90,
                textAlign = TextAlign.Center
            )
            VerticalSpacer(16.dp)
        }
    }
}

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
                            .background(Colors.Green40, shape = Dimens.Shape.Circle),
                )
            }
        }
    }
}

@Composable
fun GratefulnessOverviewBody(
    modifier: Modifier = Modifier,
    items: List<Gratefulness>,
    quote: Quote?,
    insights: List<String>,
    onDelete: (Int) -> Unit = {},
    onCreate: () -> Unit = {},
    onSeeMore: () -> Unit = {},
) {
    GratefulnessOverviewHistory(
        modifier = modifier,
        items = items.take(7),
        onDelete = onDelete,
        onCreate = onCreate,
        onSeeMore = onSeeMore
    )
    quote?.let {
        GratefulnessOverviewQuote(
            modifier = modifier,
            quote = it
        )
    }
    GratefulnessOverviewInsight(
        modifier = modifier,
        insights = insights,
        onCreate = onCreate
    )
}

@Composable
fun GratefulnessOverviewQuote(
    modifier: Modifier = Modifier,
    quote: Quote,
) {
    SectionHeader(
        modifier = modifier,
        title = Res.string.quotes
    )
    Card(
        modifier = modifier,
        colors =
            CardColors(
                containerColor = Colors.Gray5,
                contentColor = Color.Unspecified,
                disabledContentColor = Colors.Gray5,
                disabledContainerColor = Color.Unspecified
            ),
        shape = Dimens.Shape.ExtraLarge
    ) {
        Column(
            modifier = Modifier.paddingAllSmall(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Icon(
                painter = painterResource(Drawables.Icons.Filled.Quotes),
                contentDescription = null,
                tint = Colors.Green40
            )
            Text(
                text = "\"${quote.content}\"",
                style = TextStyles.headingXsRegular(),
                color = Colors.Gray80
            )
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = "\\u2014${quote.author}",
                    style = TextStyles.headingXsRegular(),
                    color = Colors.Gray80
                )
            }
        }
    }
}

@Composable
fun GratefulnessOverviewHistory(
    modifier: Modifier = Modifier,
    items: List<Gratefulness>,
    onCreate: () -> Unit,
    onDelete: (Int) -> Unit,
    onSeeMore: () -> Unit = {},
) {
    SectionHeader(
        modifier = modifier,
        title = Res.string.gratitude_history,
        onSeeMore = onSeeMore
    )
    if (items.isEmpty()) {
        NotFoundHorizontalLayout(
            modifier = modifier,
            containerColor = Colors.Gray5,
            title = Res.string.lets_set_up_daily_gratitude_and_affirmations,
            subtitle = Res.string.add_new_gratitute,
            image = Drawables.Images.GratefulnessSelfLove,
            onClick = onCreate
        )
    } else {
        Column(
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items.forEach { gratefulness ->
                GratefulnessHistoryCard(
                    modifier = modifier,
                    gratefulness = gratefulness,
                    onDelete = onDelete
                )
            }
        }
    }
}

@Composable
fun GratefulnessOverviewInsight(
    modifier: Modifier = Modifier,
    insights: List<String>,
    onCreate: () -> Unit = {},
) {
    SectionHeader(
        modifier = modifier,
        title = Res.string.gratitude_and_affirmations
    )
    if (insights.isEmpty()) {
        NotFoundVerticalLayout(
            modifier = modifier,
            title = Res.string.gratefulness_insight_title,
            subtitle = Res.string.gratefulness_insight_description,
            actionText = Res.string.gratefulness_insight_action,
            image = Drawables.Images.GratefulnessInsight,
            onClick = onCreate
        )
    } else {
        GratefulnessOverviewInsightBody(
            modifier = modifier,
            items = insights
        )
    }
}

@Composable
fun GratefulnessOverviewInsightBody(
    modifier: Modifier = Modifier,
    items: List<String>,
) {
    Card(
        colors =
            CardColors(
                containerColor = Colors.Gray60,
                contentColor = Colors.Brown80,
                disabledContainerColor = Colors.Gray5,
                disabledContentColor = Colors.Gray60
            ),
        shape = Dimens.Shape.ExtraLarge
    ) {
    }
}

@Composable
@Preview
private fun Preview() {
    GratefulnessOverviewContent(
        state =
            GratefulnessOverviewContract.State(
                items =
                    listOf(
                        Gratefulness(
                            id = 1,
                            iAmGratefulFor = "iAmGratefulFor",
                            iAmThankfulFor = "iAmThankfulFor",
                            smallThingIAppreciate = "smallThingIAppreciate",
                            description = "description"
                        ),
                        Gratefulness(
                            id = 2,
                            iAmGratefulFor = "iAmGratefulFor",
                            iAmThankfulFor = "iAmThankfulFor",
                            smallThingIAppreciate = "smallThingIAppreciate",
                            description = "description"
                        )
                    )
            )
    )
}