package com.joohnq.moodapp.view.constants

import com.joohnq.moodapp.view.icons.MoodDepressed
import com.joohnq.moodapp.view.icons.MoodHappy
import com.joohnq.moodapp.view.icons.MoodNeutral
import com.joohnq.moodapp.view.icons.MoodOverjoyed
import com.joohnq.moodapp.view.icons.MoodSad
import com.joohnq.moodapp.view.icons.Target
import moodapp.composeapp.generated.resources.Res
import moodapp.composeapp.generated.resources.ic_arrow
import moodapp.composeapp.generated.resources.ic_arrow_open
import moodapp.composeapp.generated.resources.ic_check
import moodapp.composeapp.generated.resources.ic_close
import moodapp.composeapp.generated.resources.ic_drug_store
import moodapp.composeapp.generated.resources.ic_calendar
import moodapp.composeapp.generated.resources.ic_document
import moodapp.composeapp.generated.resources.ic_heart
import moodapp.composeapp.generated.resources.ic_medicine
import moodapp.composeapp.generated.resources.ic_nothing
import moodapp.composeapp.generated.resources.ic_question
import moodapp.composeapp.generated.resources.ic_resize
import moodapp.composeapp.generated.resources.ic_sad_face
import moodapp.composeapp.generated.resources.logo
import moodapp.composeapp.generated.resources.ic_warning
import moodapp.composeapp.generated.resources.mood_depressed
import moodapp.composeapp.generated.resources.ic_user
import moodapp.composeapp.generated.resources.mood_happy
import moodapp.composeapp.generated.resources.mood_neutral
import moodapp.composeapp.generated.resources.mood_overjoyed
import moodapp.composeapp.generated.resources.mood_sad
import moodapp.composeapp.generated.resources.onboarding_sought_professional_help_image
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
    }

    object Images {
        val WelcomeFirstScreenImage = Res.drawable.welcome_first_screen_image
        val WelcomeHealthStateImage = Res.drawable.welcome_health_state_image
        val WelcomeIntelligentImage = Res.drawable.welcome_intelligent_image
        val WelcomeResourcesImage = Res.drawable.welcome_resources_image
        val WelcomeCommunityImage = Res.drawable.welcome_community_image

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
}