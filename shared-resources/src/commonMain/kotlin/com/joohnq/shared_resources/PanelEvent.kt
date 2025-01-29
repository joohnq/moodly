package com.joohnq.shared_resources

sealed interface PanelEvent {
    data object OnGoBack : PanelEvent
    data object OnAdd : PanelEvent
}