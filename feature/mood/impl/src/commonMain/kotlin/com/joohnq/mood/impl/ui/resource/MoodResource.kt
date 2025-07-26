package com.joohnq.mood.impl.ui.resource

import com.joohnq.mood.api.entity.Mood.Companion.DEPRESSED
import com.joohnq.mood.api.entity.Mood.Companion.HAPPY
import com.joohnq.mood.api.entity.Mood.Companion.NEUTRAL
import com.joohnq.mood.api.entity.Mood.Companion.OVERJOYED
import com.joohnq.mood.api.entity.Mood.Companion.SAD
import com.joohnq.mood.api.entity.MoodPalette
import com.joohnq.mood.api.property.MoodProperties
import com.joohnq.shared_resources.*
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.Drawables
import com.joohnq.ui.entity.Assets
import org.jetbrains.compose.resources.StringResource

sealed class MoodResource(
    override val id: Int,
    val assets: Assets,
    val text: StringResource,
    override val healthLevel: Int,
    val palette: MoodPalette,
) : MoodProperties {
    data object Depressed :
        MoodResource(
            id = DEPRESSED.id,
            assets =
                Assets(
                    imageVector = Drawables.Mood.DepressedVectorPainter,
                    icon = Drawables.Icons.Filled.MoodDepressed,
                    secondaryIcon = Drawables.Mood.FaceDepressedNew
                ),
            text = Res.string.depressed,
            healthLevel = DEPRESSED.healthLevel,
            palette =
                MoodPalette(
                    faceBackgroundColor = Colors.Purple40,
                    faceColor = Colors.Purple90,
                    backgroundColor = Colors.Purple10,
                    subColor = Colors.Purple30,
                    color = Colors.Purple40,
                    moodScreenBackgroundColor = Colors.Purple30,
                    moodScreenInactiveColor = Colors.Purple50,
                    moodScreenTraceColor = Colors.Purple40,
                    moodScreenMoodFaceBackgroundColor = Colors.Purple20,
                    moodScreenMoodFaceColor = Colors.Purple80,
                    barColor = Colors.Purple30,
                    barFaceColor = Colors.Purple50,
                    imageColor = Colors.Purple60
                )
        )

    data object Sad : MoodResource(
        id = SAD.id,
        assets =
            Assets(
                imageVector = Drawables.Mood.SadVectorPainter,
                icon = Drawables.Icons.Filled.MoodSad,
                secondaryIcon = Drawables.Mood.FaceSadNew
            ),
        text = Res.string.sad,
        healthLevel = SAD.healthLevel,
        palette =
            MoodPalette(
                faceBackgroundColor = Colors.Orange40,
                faceColor = Colors.Orange90,
                backgroundColor = Colors.Orange10,
                subColor = Colors.Orange30,
                color = Colors.Orange40,
                moodScreenBackgroundColor = Colors.Orange40,
                moodScreenInactiveColor = Colors.Orange60,
                moodScreenTraceColor = Colors.Orange60,
                moodScreenMoodFaceBackgroundColor = Colors.Orange30,
                moodScreenMoodFaceColor = Colors.Orange80,
                barColor = Colors.Orange40,
                barFaceColor = Colors.Orange60,
                imageColor = Colors.Orange50
            )
    )

    data object Neutral :
        MoodResource(
            id = NEUTRAL.id,
            assets =
                Assets(
                    imageVector = Drawables.Mood.NeutralVectorPainter,
                    icon = Drawables.Icons.Filled.MoodNeutral,
                    secondaryIcon = Drawables.Mood.FaceNeutralNew
                ),
            text = Res.string.neutral,
            healthLevel = NEUTRAL.healthLevel,
            palette =
                MoodPalette(
                    faceBackgroundColor = Colors.Brown60,
                    faceColor = Colors.Brown90,
                    backgroundColor = Colors.Brown20,
                    subColor = Colors.Brown40,
                    color = Colors.Brown60,
                    moodScreenBackgroundColor = Colors.Brown60,
                    moodScreenInactiveColor = Colors.Brown80,
                    moodScreenTraceColor = Colors.Brown80,
                    moodScreenMoodFaceBackgroundColor = Colors.Brown40,
                    moodScreenMoodFaceColor = Colors.Brown80,
                    barColor = Colors.Brown50,
                    barFaceColor = Colors.Brown70,
                    imageColor = Colors.Brown70
                )
        )

    data object Happy : MoodResource(
        id = HAPPY.id,
        assets =
            Assets(
                imageVector = Drawables.Mood.HappyVectorPainter,
                icon = Drawables.Icons.Filled.MoodHappy,
                secondaryIcon = Drawables.Mood.FaceHappyNew
            ),
        text = Res.string.happy,
        healthLevel = HAPPY.healthLevel,
        palette =
            MoodPalette(
                faceBackgroundColor = Colors.Yellow40,
                faceColor = Colors.Yellow90,
                backgroundColor = Colors.Yellow10,
                subColor = Colors.Yellow40,
                color = Colors.Yellow50,
                moodScreenBackgroundColor = Colors.Yellow40,
                moodScreenInactiveColor = Colors.Yellow60,
                moodScreenTraceColor = Colors.Yellow60,
                moodScreenMoodFaceBackgroundColor = Colors.Yellow20,
                moodScreenMoodFaceColor = Colors.Yellow80,
                barColor = Colors.Yellow40,
                barFaceColor = Colors.Yellow60,
                imageColor = Colors.Yellow70
            )
    )

    data object Overjoyed :
        MoodResource(
            id = OVERJOYED.id,
            assets =
                Assets(
                    imageVector = Drawables.Mood.OverjoyedVectorPainter,
                    icon = Drawables.Icons.Filled.MoodOverjoyed,
                    secondaryIcon = Drawables.Mood.FaceOverjoyedNew
                ),
            text = Res.string.overjoyed,
            healthLevel = OVERJOYED.healthLevel,
            palette =
                MoodPalette(
                    faceBackgroundColor = Colors.Green50,
                    faceColor = Colors.Green90,
                    backgroundColor = Colors.Green10,
                    subColor = Colors.Green40,
                    color = Colors.Green50,
                    moodScreenBackgroundColor = Colors.Green50,
                    moodScreenInactiveColor = Colors.Green50,
                    moodScreenTraceColor = Colors.Green50,
                    moodScreenMoodFaceBackgroundColor = Colors.Green30,
                    moodScreenMoodFaceColor = Colors.Green80,
                    barColor = Colors.Green50,
                    barFaceColor = Colors.Green70,
                    imageColor = Colors.Green60
                )
        )
}
