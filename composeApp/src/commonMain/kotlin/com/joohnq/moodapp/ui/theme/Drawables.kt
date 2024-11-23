package com.joohnq.moodapp.ui.theme

import com.joohnq.moodapp.ui.icons.MoodDepressed
import com.joohnq.moodapp.ui.icons.MoodHappy
import com.joohnq.moodapp.ui.icons.MoodNeutral
import com.joohnq.moodapp.ui.icons.MoodOverjoyed
import com.joohnq.moodapp.ui.icons.MoodSad
import com.joohnq.moodapp.ui.icons.Target
import moodapp.composeapp.generated.resources.Res
import moodapp.composeapp.generated.resources.freud_score_background
import moodapp.composeapp.generated.resources.health_journal_background
import moodapp.composeapp.generated.resources.ic_add
import moodapp.composeapp.generated.resources.ic_arrow
import moodapp.composeapp.generated.resources.ic_arrow_chevron
import moodapp.composeapp.generated.resources.ic_arrow_open
import moodapp.composeapp.generated.resources.ic_calendar
import moodapp.composeapp.generated.resources.ic_chart
import moodapp.composeapp.generated.resources.ic_chat
import moodapp.composeapp.generated.resources.ic_check
import moodapp.composeapp.generated.resources.ic_close
import moodapp.composeapp.generated.resources.ic_document
import moodapp.composeapp.generated.resources.ic_document_health
import moodapp.composeapp.generated.resources.ic_drug_store
import moodapp.composeapp.generated.resources.ic_edit
import moodapp.composeapp.generated.resources.ic_edit_2
import moodapp.composeapp.generated.resources.ic_flag
import moodapp.composeapp.generated.resources.ic_happy_face
import moodapp.composeapp.generated.resources.ic_head
import moodapp.composeapp.generated.resources.ic_heart
import moodapp.composeapp.generated.resources.ic_home
import moodapp.composeapp.generated.resources.ic_hospital_bed
import moodapp.composeapp.generated.resources.ic_medicine
import moodapp.composeapp.generated.resources.ic_mood_neutral
import moodapp.composeapp.generated.resources.ic_moon
import moodapp.composeapp.generated.resources.ic_nothing
import moodapp.composeapp.generated.resources.ic_question
import moodapp.composeapp.generated.resources.ic_resize
import moodapp.composeapp.generated.resources.ic_sad_face
import moodapp.composeapp.generated.resources.ic_sleep
import moodapp.composeapp.generated.resources.ic_sun
import moodapp.composeapp.generated.resources.ic_trash
import moodapp.composeapp.generated.resources.ic_user
import moodapp.composeapp.generated.resources.ic_warning
import moodapp.composeapp.generated.resources.ic_warning_outlined
import moodapp.composeapp.generated.resources.logo
import moodapp.composeapp.generated.resources.mood_background
import moodapp.composeapp.generated.resources.mood_depressed
import moodapp.composeapp.generated.resources.mood_happy
import moodapp.composeapp.generated.resources.mood_neutral
import moodapp.composeapp.generated.resources.mood_overjoyed
import moodapp.composeapp.generated.resources.mood_sad
import moodapp.composeapp.generated.resources.onboarding_sought_professional_help_image
import moodapp.composeapp.generated.resources.shape_bottom_navigation
import moodapp.composeapp.generated.resources.sleep_quality_background
import moodapp.composeapp.generated.resources.stress_level_background
import moodapp.composeapp.generated.resources.welcome_community_image
import moodapp.composeapp.generated.resources.welcome_first_screen_image
import moodapp.composeapp.generated.resources.welcome_health_state_image
import moodapp.composeapp.generated.resources.welcome_intelligent_image
import moodapp.composeapp.generated.resources.welcome_resources_image

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