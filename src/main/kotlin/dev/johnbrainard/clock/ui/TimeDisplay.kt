package dev.johnbrainard.clock.ui

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.johnbrainard.clock.Clock

@Composable
fun TimeDisplay(
	clock: Clock,
	modifier: Modifier = Modifier
) {
	Row(modifier = modifier) {
		val textWidth = 225.dp
		Text(
			style = ClockStyle.clockFaceStyle,
			modifier = Modifier.width(textWidth),
			text = clock.hour
		)
		Text(
			style = ClockStyle.separatorStyle(clock.separatorOn),
			text = ":"
		)
		Text(
			style = ClockStyle.clockFaceStyle,
			modifier = Modifier.width(textWidth),
			text = clock.minute
		)
	}
}

