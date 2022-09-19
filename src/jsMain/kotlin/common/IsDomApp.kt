package common

import androidx.compose.runtime.Composable
import androidx.compose.runtime.currentComposer
import org.jetbrains.compose.web.internal.runtime.ComposeWebInternalApi
import org.jetbrains.compose.web.internal.runtime.DomApplier

@OptIn(ComposeWebInternalApi::class)
@Composable
fun isDomApp() = currentComposer.applier is DomApplier