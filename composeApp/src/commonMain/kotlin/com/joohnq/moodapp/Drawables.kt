package com.joohnq.moodapp

import com.joohnq.moodapp.icons.MoodDepressed
import com.joohnq.moodapp.icons.MoodHappy
import com.joohnq.moodapp.icons.MoodNeutral
import com.joohnq.moodapp.icons.MoodOverjoyed
import com.joohnq.moodapp.icons.MoodSad
import com.joohnq.moodapp.icons.MoodSad
import com.joohnq.moodapp.icons.Target
import moodapp.composeapp.generated.resources.Res
import moodapp.composeapp.generated.resources.ic_arrow
import moodapp.composeapp.generated.resources.ic_arrow_open
import moodapp.composeapp.generated.resources.ic_check
import moodapp.composeapp.generated.resources.ic_close
import moodapp.composeapp.generated.resources.ic_question
import moodapp.composeapp.generated.resources.ic_question
import moodapp.composeapp.generated.resources.ic_resize
import moodapp.composeapp.generated.resources.mood_depressed
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
    }

    object Images {
        val WelcomeFirstScreenImage = Res.drawable.welcome_first_screen_image
        val WelcomeHealthStateImage = Res.drawable.welcome_health_state_image
        val WelcomeIntelligentImage = Res.drawable.welcome_intelligent_image
        val WelcomeResourcesImage = Res.drawable.welcome_resources_image
        val WelcomeCommunityImage = Res.drawable.welcome_community_image

        val OnboardingSoughtProfessionalHelp = Res.drawable.onboarding_sought_professional_help_image
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