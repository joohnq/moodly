package com.joohnq.core.ui.event

sealed interface PanelEvent {
    data object OnGoBack : PanelEvent
    data object OnAdd : PanelEvent
}