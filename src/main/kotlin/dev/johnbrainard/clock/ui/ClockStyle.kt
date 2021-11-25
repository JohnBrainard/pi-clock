package dev.johnbrainard.clock.ui

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.platform.Font
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp

object ClockStyle {
	val backgroundColor = Color.Black
	val primaryColor = Color(145, 252, 5)

	val clockFace: FontFamily = FontFamily(
		Font(
			resource = "monkey.ttf",
			weight = FontWeight.Normal,
			style = FontStyle.Normal
		)
	)

	val clockFaceStyle: TextStyle = TextStyle(
		fontSize = 300.sp,
		fontFamily = clockFace,
		textAlign = TextAlign.End,
		color = primaryColor
	)

	val dateFaceStyle: TextStyle = TextStyle(
		fontSize = 32.sp,
		fontFamily = clockFace,
		color = primaryColor
	)

	val separatorStyleOn: TextStyle = clockFaceStyle.copy(
		color = primaryColor
	)

	val separatorStyleOff = clockFaceStyle.copy(
		color = Color.Transparent
	)

	fun separatorStyle(on: Boolean) = if (on) {
		separatorStyleOn
	} else {
		separatorStyleOff
	}
}