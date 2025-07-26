package com.joohnq.freud_score.impl.parameter

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.joohnq.freud_score.impl.resource.FreudScoreResource

class FreudScoreResourceParameterProvider : PreviewParameterProvider<FreudScoreResource> {
    override val values = sequenceOf(
        FreudScoreResource.NotAvailable,
        FreudScoreResource.Unhealthy(10),
        FreudScoreResource.AtRisk(30),
        FreudScoreResource.Stable(50),
        FreudScoreResource.MostlyHealthy(70),
        FreudScoreResource.Healthy(90)
    )
}
