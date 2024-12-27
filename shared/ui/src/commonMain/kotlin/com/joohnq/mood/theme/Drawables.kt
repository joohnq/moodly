package com.joohnq.mood.theme

import com.joohnq.mood.icons.MoodDepressed
import com.joohnq.mood.icons.MoodHappy
import com.joohnq.mood.icons.MoodNeutral
import com.joohnq.mood.icons.MoodOverjoyed
import com.joohnq.mood.icons.MoodSad
import com.joohnq.mood.icons.Target
import moodapp.shared.ui.generated.resources.Res
import moodapp.shared.ui.generated.resources.freud_score_background
import moodapp.shared.ui.generated.resources.health_journal_background
import moodapp.shared.ui.generated.resources.ic_add
import moodapp.shared.ui.generated.resources.ic_arrow
import moodapp.shared.ui.generated.resources.ic_arrow_chevron
import moodapp.shared.ui.generated.resources.ic_arrow_open
import moodapp.shared.ui.generated.resources.ic_calendar
import moodapp.shared.ui.generated.resources.ic_chart
import moodapp.shared.ui.generated.resources.ic_chat
import moodapp.shared.ui.generated.resources.ic_check
import moodapp.shared.ui.generated.resources.ic_clock
import moodapp.shared.ui.generated.resources.ic_close
import moodapp.shared.ui.generated.resources.ic_document
import moodapp.shared.ui.generated.resources.ic_document_health
import moodapp.shared.ui.generated.resources.ic_drug_store
import moodapp.shared.ui.generated.resources.ic_edit
import moodapp.shared.ui.generated.resources.ic_edit_2
import moodapp.shared.ui.generated.resources.ic_flag
import moodapp.shared.ui.generated.resources.ic_happy_face
import moodapp.shared.ui.generated.resources.ic_head
import moodapp.shared.ui.generated.resources.ic_heart
import moodapp.shared.ui.generated.resources.ic_home
import moodapp.shared.ui.generated.resources.ic_hospital_bed
import moodapp.shared.ui.generated.resources.ic_medicine
import moodapp.shared.ui.generated.resources.ic_minus
import moodapp.shared.ui.generated.resources.ic_mood_neutral
import moodapp.shared.ui.generated.resources.ic_moon
import moodapp.shared.ui.generated.resources.ic_more_horizontal
import moodapp.shared.ui.generated.resources.ic_nothing
import moodapp.shared.ui.generated.resources.ic_question
import moodapp.shared.ui.generated.resources.ic_redo
import moodapp.shared.ui.generated.resources.ic_resize
import moodapp.shared.ui.generated.resources.ic_sad_face
import moodapp.shared.ui.generated.resources.ic_sleep
import moodapp.shared.ui.generated.resources.ic_sun
import moodapp.shared.ui.generated.resources.ic_trash
import moodapp.shared.ui.generated.resources.ic_undo
import moodapp.shared.ui.generated.resources.ic_user
import moodapp.shared.ui.generated.resources.ic_warning
import moodapp.shared.ui.generated.resources.ic_warning_outlined
import moodapp.shared.ui.generated.resources.logo
import moodapp.shared.ui.generated.resources.mood_background
import moodapp.shared.ui.generated.resources.mood_depressed
import moodapp.shared.ui.generated.resources.mood_happy
import moodapp.shared.ui.generated.resources.mood_neutral
import moodapp.shared.ui.generated.resources.mood_overjoyed
import moodapp.shared.ui.generated.resources.mood_sad
import moodapp.shared.ui.generated.resources.onboarding_sought_professional_help_image
import moodapp.shared.ui.generated.resources.shape_bottom_navigation
import moodapp.shared.ui.generated.resources.sleep_quality_background
import moodapp.shared.ui.generated.resources.stress_level_background
import moodapp.shared.ui.generated.resources.welcome_community_image
import moodapp.shared.ui.generated.resources.welcome_first_screen_image
import moodapp.shared.ui.generated.resources.welcome_health_state_image
import moodapp.shared.ui.generated.resources.welcome_intelligent_image
import moodapp.shared.ui.generated.resources.welcome_resources_image

object Drawables {
    object Icons {
        val ArrowOpen = Res.drawable.ic_arrow_open
        val Arrow = Res.drawable.ic_arrow
        val Check = Res.drawable.ic_check
        val Close = Res.drawable.ic_close
        val Question = Res.drawable.ic_question
        val Resize = Res.drawable.ic_resize
        val Medicine = Res.drawable.ic_medicine
        val DrugStore = Res.drawable.ic_drug_store
        val Nothing = Res.drawable.ic_nothing
        val Logo = Res.drawable.logo
        val User = Res.drawable.ic_user
        val Warning = Res.drawable.ic_warning
        val Calendar = Res.drawable.ic_calendar
        val Heart = Res.drawable.ic_heart
        val Document = Res.drawable.ic_document
        val SadFace = Res.drawable.ic_sad_face
        val DocumentHealth = Res.drawable.ic_document_health
        val HospitalBed = Res.drawable.ic_hospital_bed
        val HappyFace = Res.drawable.ic_happy_face
        val Head = Res.drawable.ic_head
        val Add = Res.drawable.ic_add
        val Home = Res.drawable.ic_home
        val Chat = Res.drawable.ic_chat
        val ArrowChevron = Res.drawable.ic_arrow_chevron
        val Flag = Res.drawable.ic_flag
        val WarningOutlined = Res.drawable.ic_warning_outlined
        val Sleep = Res.drawable.ic_sleep
        val Sun = Res.drawable.ic_sun
        val Moon = Res.drawable.ic_moon
        val MoodNeutral = Res.drawable.ic_mood_neutral
        val Chart = Res.drawable.ic_chart
        val Edit = Res.drawable.ic_edit
        val Edit2 = Res.drawable.ic_edit_2
        val Trash = Res.drawable.ic_trash
        val MoreHorizontal = Res.drawable.ic_more_horizontal
        val Clock = Res.drawable.ic_clock
        val Undo = Res.drawable.ic_undo
        val Redo = Res.drawable.ic_redo
        val Minus = Res.drawable.ic_minus
    }

    object Images {
        val WelcomeFirstScreenImage = Res.drawable.welcome_first_screen_image
        val WelcomeHealthStateImage = Res.drawable.welcome_health_state_image
        val WelcomeIntelligentImage = Res.drawable.welcome_intelligent_image
        val WelcomeResourcesImage = Res.drawable.welcome_resources_image
        val WelcomeCommunityImage = Res.drawable.welcome_community_image

        val FreudScoreBackground = Res.drawable.freud_score_background
        val MoodBackground = Res.drawable.mood_background
        val StressLevelBackground = Res.drawable.stress_level_background
        val SleepQualityBackground = Res.drawable.sleep_quality_background
        val HealthJournalBackground = Res.drawable.health_journal_background

        val OnboardingSoughtProfessionalHelp =
            Res.drawable.onboarding_sought_professional_help_image
    }

    object Mood {
        val Depressed = Res.drawable.mood_depressed
        val Sad = Res.drawable.mood_sad
        val Neutral = Res.drawable.mood_neutral
        val Happy = Res.drawable.mood_happy
        val Overjoyed = Res.drawable.mood_overjoyed

        val DepressedVectorPainter = MoodDepressed
        val SadVectorPainter = MoodSad
        val NeutralVectorPainter = MoodNeutral
        val HappyVectorPainter = MoodHappy
        val OverjoyedVectorPainter = MoodOverjoyed

        val TargetVectorPainter = Target
    }

    object Shape {
        val BottomNavigation = Res.drawable.shape_bottom_navigation
    }
}