package com.joohnq.moodapp.view.screens.mood

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.joohnq.moodapp.MoodsManager
import com.joohnq.moodapp.entities.Icon
import com.joohnq.moodapp.entities.Mood
import com.joohnq.moodapp.entities.StatsRecord
import com.joohnq.moodapp.sharedViewModel
import com.joohnq.moodapp.view.components.ButtonWithIcon
import com.joohnq.moodapp.view.components.MoodFace
import com.joohnq.moodapp.view.components.TextStyles
import com.joohnq.moodapp.view.components.TopBarDark
import com.joohnq.moodapp.view.constants.Colors
import com.joohnq.moodapp.view.constants.Drawables
import com.joohnq.moodapp.view.state.UiState.Companion.getValue
import com.joohnq.moodapp.viewmodel.StatsViewModel
import moodapp.composeapp.generated.resources.Res
import moodapp.composeapp.generated.resources.mood
import moodapp.composeapp.generated.resources.next
import moodapp.composeapp.generated.resources.previous
import moodapp.composeapp.generated.resources.your_mood_is
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun MoodScreenUi(
    statsRecord: StatsRecord,
    hasNext: Boolean,
    hasPrevious: Boolean,
    onAction: (MoodAction) -> Unit = {},
) {
    Scaffold(
        containerColor = Colors.Brown10,
    ) { scaffoldPadding ->
        val padding = PaddingValues(
            top = scaffoldPadding.calculateTopPadding(),
            bottom = scaffoldPadding.calculateBottomPadding() + 100.dp,
            start = scaffoldPadding.calculateStartPadding(LayoutDirection.Ltr),
            end = scaffoldPadding.calculateEndPadding(LayoutDirection.Rtl)
        )
        BoxWithConstraints {
            val height = maxHeight * 0.5f
            Box(
                modifier = Modifier
                    .background(color = statsRecord.mood.palette.backgroundColor)
                    .height(height)
                    .paint(
                        painter = painterResource(Drawables.Images.MoodBackground),
                        contentScale = ContentScale.FillBounds,
                        colorFilter = ColorFilter.tint(color = statsRecord.mood.palette.subColor)
                    ),
            ) {
                Box(contentAlignment = Alignment.TopCenter, modifier = Modifier.fillMaxSize()) {
                    TopBarDark(
                        modifier = Modifier.padding(top = padding.calculateTopPadding())
                            .padding(horizontal = 20.dp),
                        text = Res.string.mood,
                        onGoBack = { onAction(MoodAction.GoBack) }
                    )
                }
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.fillMaxSize()
                ) {
                    Column(
                        modifier = Modifier.padding(horizontal = 20.dp)
                            .padding(top = padding.calculateTopPadding()).fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = stringResource(Res.string.your_mood_is),
                            style = TextStyles.BoldXL(),
                            color = statsRecord.mood.palette.moodScreenMoodFaceColor
                        )
                        Text(
                            text = stringResource(statsRecord.mood.text),
                            style = TextStyles.ExtraBold2XL(),
                            color = statsRecord.mood.palette.moodScreenMoodFaceColor
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            if (hasPrevious)
                                ButtonWithIcon(
                                    onClick = { onAction(MoodAction.Previous) },
                                    icon = Icon(
                                        icon = Drawables.Icons.ArrowChevron,
                                        modifier = Modifier.size(24.dp),
                                        tint = statsRecord.mood.palette.color,
                                        contentDescription = Res.string.previous
                                    ),
                                    colors = ButtonColors(
                                        containerColor = Colors.Transparent,
                                        contentColor = statsRecord.mood.palette.color,
                                        disabledContainerColor = Colors.Transparent,
                                        disabledContentColor = statsRecord.mood.palette.color,
                                    ),
                                    modifier = Modifier.size(48.dp),
                                )
                            MoodFace(
                                modifier = Modifier.size(96.dp),
                                mood = statsRecord.mood
                            )
                            if (hasNext)
                                ButtonWithIcon(
                                    onClick = { onAction(MoodAction.Next) },
                                    icon = Icon(
                                        icon = Drawables.Icons.ArrowChevron,
                                        modifier = Modifier.size(24.dp).rotate(180f),
                                        tint = statsRecord.mood.palette.color,
                                        contentDescription = Res.string.next
                                    ),
                                    colors = ButtonColors(
                                        containerColor = Colors.Transparent,
                                        contentColor = statsRecord.mood.palette.color,
                                        disabledContainerColor = Colors.Transparent,
                                        disabledContentColor = statsRecord.mood.palette.color,
                                    ),
                                    modifier = Modifier.size(48.dp),
                                )
                        }
                    }
                }
                Box(
                    modifier = Modifier.fillMaxSize().absoluteOffset(y = 40.dp),
                    contentAlignment = Alignment.BottomCenter
                ) {
                    IconButton(
                        onClick = { onAction(MoodAction.GoToAddMood) },
                        modifier = Modifier.size(80.dp)
                            .background(color = Colors.Brown80, shape = CircleShape)
                    ) {
                        Icon(
                            painter = painterResource(Drawables.Icons.Add),
                            contentDescription = null,
                            tint = Colors.White,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun MoodScreen(
    statsRecord: StatsRecord,
    statsViewModel: StatsViewModel = sharedViewModel(),
    navigation: NavHostController
) {
    val moodsState by statsViewModel.statsState.collectAsState()
    var hasNext by remember { mutableStateOf<StatsRecord?>(null) }
    var hasPrevious by remember { mutableStateOf<StatsRecord?>(null) }
    var currentStatsRecord by remember { mutableStateOf(statsRecord) }

    LaunchedEffect(currentStatsRecord) {
        hasNext = MoodsManager.getNext(currentStatsRecord, moodsState.statsRecords.getValue())
        hasPrevious =
            MoodsManager.getPrevious(currentStatsRecord, moodsState.statsRecords.getValue())
    }

    MoodScreenUi(
        statsRecord = currentStatsRecord,
        hasNext = hasNext != null,
        hasPrevious = hasPrevious != null,
        onAction = {
            when (it) {
                MoodAction.GoToAddMood -> {

                }

                MoodAction.GoBack -> navigation.popBackStack()
                MoodAction.Next -> hasNext?.run {
                    currentStatsRecord = this
                }

                MoodAction.Previous -> hasPrevious?.run {
                    currentStatsRecord = this
                }
            }
        },
    )
}

@Preview
@Composable
fun MoodScreenPreview() {
    MoodScreenUi(
        statsRecord = StatsRecord.init().copy(mood = Mood.Overjoyed),
        hasPrevious = true,
        hasNext = true
    )
}

@Preview
@Composable
fun MoodScreenPreview2() {
    MoodScreenUi(
        statsRecord = StatsRecord.init().copy(mood = Mood.Happy),
        hasPrevious = true,
        hasNext = true
    )
}

@Preview
@Composable
fun MoodScreenPreview3() {
    MoodScreenUi(
        statsRecord = StatsRecord.init().copy(mood = Mood.Neutral),
        hasPrevious = true,
        hasNext = true
    )
}

@Preview
@Composable
fun MoodScreenPreview4() {
    MoodScreenUi(
        statsRecord = StatsRecord.init().copy(mood = Mood.Sad),
        hasPrevious = true,
        hasNext = true
    )
}

@Preview
@Composable
fun MoodScreenPreview5() {
    MoodScreenUi(
        statsRecord = StatsRecord.init().copy(mood = Mood.Depressed),
        hasPrevious = true,
        hasNext = true
    )
}

