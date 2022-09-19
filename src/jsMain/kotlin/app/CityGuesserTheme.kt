package app

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Shapes
import androidx.compose.material.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.UiComposable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp

object CityGuesserTheme {
    val typography: Typography = Typography(
        defaultFontFamily = FontFamily.Cursive
    )
    val colors: Colors
        @Composable
        get() = MaterialTheme.colors.copy(
            primary = Color(0xFFe27171),
            primaryVariant = Color(0xFFffa29f),
            secondary = Color(0xFFFCFAF2),
            secondaryVariant = Color(0xFFffffff),
            surface = Color(0xFFe27171),
            background = Color(0xFFFCFAF2),
            error = Color(0xFFe27171)
        )
    val shapes: Shapes = Shapes(
        small = RoundedCornerShape(0.dp),
        medium = RoundedCornerShape(0.dp),
        large = RoundedCornerShape(0.dp)
    )
}

@UiComposable
@Composable
fun CityGuesserTheme(content: @Composable () -> Unit) = MaterialTheme(
    typography = CityGuesserTheme.typography,
    colors = CityGuesserTheme.colors,
    shapes = CityGuesserTheme.shapes,
    content = content
)