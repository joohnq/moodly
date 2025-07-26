package com.joohnq.shared_resources

import android.graphics.BlurMaskFilter
import androidx.compose.ui.graphics.NativePaint

actual fun NativePaint.setMaskFilter(blurRadius: Float) {
    this.maskFilter = BlurMaskFilter(blurRadius, BlurMaskFilter.Blur.NORMAL)
}
