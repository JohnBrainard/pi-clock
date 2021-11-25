package dev.johnbrainard.clock.ui

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun DateDisplay(modifier: Modifier = Modifier, date: String) {
	Text(
		modifier = modifier,
		text = date,
		style = ClockStyle.dateFaceStyle
	)
}
