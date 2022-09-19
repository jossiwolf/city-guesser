package dom

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import app.QuizState
import app.asMapLocation
import dom.mapbox.*

@DomComposable
@Composable
fun DomBasedMap(quizState: QuizState) {
    val mapState = rememberMapboxMapState(
        center = quizState.correctLocation.asMapLocation(),
        maxBounds = quizState.correctLocation.bounds
    )
    val navigationControl = remember { MapboxGl.NavigationControl() }
    val scaleControl = remember { ScaleControl() }
    MapboxMap(
        state = mapState,
        onMapReady = { map ->
            map.addControl(navigationControl, NavigationControlPosition.TopStart)
            map.addControl(scaleControl)
            map.dragRotateOptions.pitchWithRotate = false
        }
    )
}