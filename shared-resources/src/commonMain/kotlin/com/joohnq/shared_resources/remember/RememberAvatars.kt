package com.joohnq.shared_resources.remember

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.joohnq.shared_resources.theme.Drawables
import org.jetbrains.compose.resources.DrawableResource

@Composable fun rememberAvatars(): List<DrawableResource> = remember { Drawables.Avatar.avatars }
