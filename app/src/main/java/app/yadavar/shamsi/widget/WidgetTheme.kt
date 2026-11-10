package app.yadavar.shamsi.widget

import androidx.compose.ui.graphics.Color

/** Colors used by the home-screen widget for one background choice. */
data class WidgetPalette(
    val bg: Color,
    val text: Color,
    val muted: Color,
    val accent: Color,
    val line: Color,
    val selected: Color,
    val dark: Boolean,
)

data class WidgetBgOption(val id: String, val label: String, val swatch: Color)

val WidgetBackgrounds = listOf(
    WidgetBgOption("lavender", "بنفش روشن", Color(0xFFE9DFF7)),
    WidgetBgOption("white", "سفید", Color(0xFFFFFFFF)),
    WidgetBgOption("mint", "نعنایی", Color(0xFFD6EEDD)),
    WidgetBgOption("sky", "آبی روشن", Color(0xFFD7E8F8)),
    WidgetBgOption("peach", "هلویی", Color(0xFFFBDDC8)),
    WidgetBgOption("rose", "صورتی", Color(0xFFF8D5E0)),
    WidgetBgOption("lemon", "لیمویی", Color(0xFFF8F0C2)),
    WidgetBgOption("ice", "یخی", Color(0xFFD0EFF3)),
    WidgetBgOption("lilac", "یاسی", Color(0xFFE2D5F6)),
    WidgetBgOption("dark", "تیره", Color(0xFF1F1B29)),
    WidgetBgOption("navy", "سرمه‌ای", Color(0xFF14213D)),
    WidgetBgOption("plum", "آلویی", Color(0xFF2E1A3C)),
    WidgetBgOption("graphite", "گرافیتی", Color(0xFF26282C)),
)

private val LightText = Color(0xFF2A2133)
private val LightMuted = Color(0xFF6B6178)
private val DarkPurple = Color(0xFF4A148C)
private val LightLine = Color(0x594A148C)

private fun light(bg: Long, selected: Long) = WidgetPalette(
    bg = Color(bg),
    text = LightText,
    muted = LightMuted,
    accent = DarkPurple,
    line = LightLine,
    selected = Color(selected),
    dark = false,
)

private fun darkPalette(bg: Long, selected: Long, accent: Long) = WidgetPalette(
    bg = Color(bg),
    text = Color(0xFFF1ECF8),
    muted = Color(0xFFA79FB5),
    accent = Color(accent),
    line = Color(accent).copy(alpha = 0.4f),
    selected = Color(selected),
    dark = true,
)

fun widgetPalette(id: String): WidgetPalette = when (id) {
    "white" -> light(0xFFFFFFFF, 0xFFEDE7F6)
    "mint" -> light(0xFFD6EEDD, 0xFFB9E0C6)
    "sky" -> light(0xFFD7E8F8, 0xFFB9D6F0)
    "peach" -> light(0xFFFBDDC8, 0xFFF6C6A6)
    "rose" -> light(0xFFF8D5E0, 0xFFF1B8CB)
    "lemon" -> light(0xFFF8F0C2, 0xFFEFE29A)
    "ice" -> light(0xFFD0EFF3, 0xFFA9E0E8)
    "lilac" -> light(0xFFE2D5F6, 0xFFCDB8EE)
    "navy" -> darkPalette(0xFF14213D, 0xFF2A3C66, 0xFFB8C7F0)
    "plum" -> darkPalette(0xFF2E1A3C, 0xFF47295E, 0xFFE0B8F5)
    "graphite" -> darkPalette(0xFF26282C, 0xFF3A3D43, 0xFFD1B3F5)
    "dark" -> WidgetPalette(
        bg = Color(0xFF1F1B29),
        text = Color(0xFFF1ECF8),
        muted = Color(0xFFA79FB5),
        accent = Color(0xFFD1B3F5),
        line = Color(0x66D1B3F5),
        selected = Color(0xFF3B3050),
        dark = true,
    )
    else -> light(0xFFE9DFF7, 0xFFD6C6EE)
}
