package app

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import lce.Lce
import lce.LceDataView

@Composable
fun CityGuesserApp(
    state: CityGuesserAppState,
    answerSubmitted: (selectedAnswer: QuizLocation) -> Unit,
    playAgain: () -> Unit
) {
    CityGuesserAppLayout(
        state = state.quizState,
        map = { quizState ->
            Text("Map unavailable")
        },
        quiz = { quizState ->
            Column(
                Modifier
                    .fillMaxHeight()
                    .border(1.dp, Color.Black)
                    .padding(
                        vertical = 32.dp,
                        horizontal = 16.dp
                    )
            ) {
                Text("City-Guesser", style = CityGuesserTheme.typography.h4)
                Spacer(Modifier.height(48.dp))
                Text("Level ${state.level}", style = CityGuesserTheme.typography.body1)
                Text("Score ${state.score}", style = CityGuesserTheme.typography.body1)
                Spacer(Modifier.height(12.dp))
                Quiz(
                    quizState = quizState,
                    submitAnswer = answerSubmitted,
                    playAgain = playAgain
                )
                Spacer(Modifier.height(8.dp))
                Text("Your Highscore: ${state.highScore}", style = CityGuesserTheme.typography.caption)
                Spacer(Modifier.height(8.dp))
                Attribution()
            }
        }
    )
}

@Composable
private fun CityGuesserAppLayout(
    state: Lce<QuizState>,
    map: @Composable (state: QuizState) -> Unit,
    quiz: @Composable (state: QuizState) -> Unit
) {
    LceDataView(state = state) { quizState ->
        Row(Modifier.fillMaxWidth()) {
            Box(Modifier.weight(.8f)) {
                map(quizState)
            }
            Box(Modifier.weight(.2f)) {
                quiz(quizState)
            }
        }
    }
}



@Composable
private fun Quiz(
    quizState: QuizState,
    submitAnswer: (selectedAnswer: QuizLocation) -> Unit,
    playAgain: () -> Unit
) = Column {
    var selectedLocation by remember { mutableStateOf<QuizLocation?>(null) }
    AnswerOptions(
        locations = quizState.locations,
        inputEnabled = quizState.answerCorrect != false,
        selectedLocation = selectedLocation,
        answerSelected = { location -> selectedLocation = location }
    )
    Spacer(Modifier.height(32.dp))
    when (quizState.answerCorrect) {
        null -> Button(
            onClick = { selectedLocation?.let(submitAnswer) },
            Modifier.fillMaxWidth(),
        ) {
            Text("Submit Answer")
        }

        true -> {}
        false -> {
            Text("Wrong answer! The correct answer was ${quizState.correctLocation.cityName}, ${quizState.correctLocation.countryName}.")
            Button(onClick = playAgain, Modifier.fillMaxWidth()) {
                Text("Play again!")
            }
        }
    }
}

@Composable
private fun AnswerOptions(
    locations: List<QuizLocation>,
    inputEnabled: Boolean,
    selectedLocation: QuizLocation?,
    answerSelected: (location: QuizLocation) -> Unit
) {
    Column(Modifier.selectableGroup()) {
        locations.forEach { location ->
            Row(
                Modifier
                    .selectable(
                        selected = location == selectedLocation,
                        enabled = inputEnabled,
                        onClick = { answerSelected(location) }
                    )
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = location == selectedLocation,
                    enabled = inputEnabled,
                    onClick = null
                )
                Spacer(Modifier.width(8.dp))
                val locationName = remember(location) { location.formattedName() }
                Text(locationName)
            }
        }
    }
}

@Composable
private fun Attribution() {
    Surface(color = MaterialTheme.colors.background, border = BorderStroke(1.dp, MaterialTheme.colors.onSurface)) {
        Column(Modifier.padding(16.dp)) {
            Text("Source code for this map is on GitHub: https://github.com/jossiwolf/CityGuesserApp/")
            Text("It was built by Jossi Wolf after Jeff Allen's version (https://jamaps.github.io/)")
        }
    }
}