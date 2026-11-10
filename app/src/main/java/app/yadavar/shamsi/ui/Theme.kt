package app.yadavar.shamsi.ui

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.lerp
import androidx.compose.ui.graphics.luminance
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class AccentOption(val id: String, val label: String, val color: Color)

/** Soft pastel color themes of the app (the first one is the default). The color is also the page background. */
val AccentOptions = listOf(
    AccentOption("purple", "بنفش", Color(0xFFCDBDEB)),
    AccentOption("indigo", "نیلی", Color(0xFFBBC6EE)),
    AccentOption("blue", "آبی", Color(0xFFB8D6F5)),
    AccentOption("teal", "سبزآبی", Color(0xFFABDDD7)),
    AccentOption("green", "سبز", Color(0xFFC1E4C3)),
    AccentOption("amber", "کهربایی", Color(0xFFF4E1A6)),
    AccentOption("orange", "نارنجی", Color(0xFFF7CCAC)),
    AccentOption("rose", "صورتی", Color(0xFFF5BCCF)),
    AccentOption("brown", "قهوه‌ای", Color(0xFFD5C2B8)),
    AccentOption("slate", "خاکستری", Color(0xFFC6D0D8)),
)

fun accentColor(name: String): Color =
    AccentOptions.firstOrNull { it.id == name }?.color ?: AccentOptions.first().color

/** Size of the texts inside the app (the widget has its own size setting). */
val AppFontOptions = listOf("sm" to "کوچک", "md" to "متوسط", "lg" to "بزرگ", "xl" to "خیلی بزرگ")

fun appTextScale(id: String): Float = when (id) {
    "sm" -> 0.9f
    "lg" -> 1.15f
    "xl" -> 1.3f
    else -> 1f
}

private val AppShapes = Shapes(
    extraSmall = RoundedCornerShape(12.dp),
    small = RoundedCornerShape(16.dp),
    medium = RoundedCornerShape(20.dp),
    large = RoundedCornerShape(24.dp),
    extraLarge = RoundedCornerShape(28.dp),
)

// Slightly larger text with roomier lines, for easier reading.
private val AppTypography = Typography(
    headlineSmall = TextStyle(fontSize = 24.sp, lineHeight = 34.sp, fontWeight = FontWeight.SemiBold),
    titleLarge = TextStyle(fontSize = 21.sp, lineHeight = 30.sp, fontWeight = FontWeight.SemiBold),
    titleMedium = TextStyle(fontSize = 17.sp, lineHeight = 26.sp, fontWeight = FontWeight.Medium),
    titleSmall = TextStyle(fontSize = 15.sp, lineHeight = 23.sp, fontWeight = FontWeight.Medium),
    bodyLarge = TextStyle(fontSize = 16.sp, lineHeight = 26.sp),
    bodyMedium = TextStyle(fontSize = 15.sp, lineHeight = 24.sp),
    bodySmall = TextStyle(fontSize = 13.sp, lineHeight = 21.sp),
    labelLarge = TextStyle(fontSize = 14.sp, lineHeight = 21.sp, fontWeight = FontWeight.Medium),
    labelMedium = TextStyle(fontSize = 12.5.sp, lineHeight = 19.sp, fontWeight = FontWeight.Medium),
)

/** Near-black on light colors, white on dark colors, so text stays readable on any background. */
fun contrastOn(background: Color): Color =
    if (background.luminance() > 0.5f) Color(0xFF211F26) else Color.White

@Composable
fun YadavarTheme(theme: String, accent: String, textScale: Float = 1f, content: @Composable () -> Unit) {
    val dark = when (theme) {
        "light" -> false
        "dark" -> true
        else -> isSystemInDarkTheme()
    }
    val seed = accentColor(accent)
    val scheme = if (dark) {
        val background = lerp(Color(0xFF121214), seed, 0.22f)
        val surface = lerp(background, seed, 0.10f)
        val onBackground = contrastOn(background)
        val primary = lerp(seed, Color.White, 0.10f)
        darkColorScheme(
            primary = primary,
            onPrimary = contrastOn(primary),
            primaryContainer = lerp(background, seed, 0.45f),
            onPrimaryContainer = contrastOn(lerp(background, seed, 0.45f)),
            secondaryContainer = lerp(background, seed, 0.40f),
            onSecondaryContainer = contrastOn(lerp(background, seed, 0.40f)),
            background = background,
            onBackground = onBackground,
            surface = surface,
            onSurface = contrastOn(surface),
            surfaceVariant = lerp(background, seed, 0.18f),
            onSurfaceVariant = contrastOn(surface).copy(alpha = 0.72f),
            surfaceContainer = lerp(background, seed, 0.16f),
            outline = lerp(seed, background, 0.45f),
        )
    } else {
        val background = lerp(Color.White, seed, 0.60f)
        val surface = lerp(Color.White, seed, 0.18f)
        val primary = lerp(seed, Color.Black, 0.42f)
        lightColorScheme(
            primary = primary,
            onPrimary = contrastOn(primary),
            primaryContainer = seed,
            onPrimaryContainer = contrastOn(seed),
            secondaryContainer = seed,
            onSecondaryContainer = contrastOn(seed),
            background = background,
            onBackground = contrastOn(background),
            surface = surface,
            onSurface = contrastOn(surface),
            surfaceVariant = lerp(Color.White, seed, 0.40f),
            onSurfaceVariant = contrastOn(surface).copy(alpha = 0.72f),
            surfaceContainer = lerp(Color.White, seed, 0.45f),
            outline = lerp(seed, Color.Black, 0.35f),
        )
    }
    val base = LocalDensity.current
    CompositionLocalProvider(LocalDensity provides Density(base.density, base.fontScale * textScale)) {
        MaterialTheme(colorScheme = scheme, shapes = AppShapes, typography = AppTypography, content = content)
    }
}
