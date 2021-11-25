package dev.johnbrainard.clock.ui

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ExperimentalGraphicsApi
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.application
import dev.johnbrainard.clock.Clock
import dev.johnbrainard.clock.ClockApplication

@Composable
fun AnalogClockDisplay(modifier: Modifier = Modifier, clock: Clock) {
	Canvas(
		modifier = Modifier.size(300.dp),
		onDraw = {
			// minute hand
			drawLine(
				color = Color.White,
				strokeWidth = 8f,
				start = Offset(150f, 150f),
				end = Offset(280f, 280f)
			)

			drawLine(
				color = Color.White,
				strokeWidth = 8f,
				start = Offset(150f, 150f),
				end = Offset(150f, 250f)
			)
		}
	)
}

enum class DisplayState {
	Time,
	Weather;

	fun next(): DisplayState = when (this) {
		Time -> Weather
		Weather -> Time
	}
}

@ExperimentalGraphicsApi
@Composable
@Preview
fun App() {
	val application = ClockApplication()
	val clockState = mutableStateOf(application.currentClock())
	val dateState = mutableStateOf(application.currentDate())
	val displayState = mutableStateOf(DisplayState.Time)

	MaterialTheme {
		Box(
			modifier = Modifier
				.fillMaxSize()
				.clickable {
					displayState.value = displayState.value.next()
				}
		) {
			DateDisplay(
				modifier = Modifier.align(Alignment.TopStart)
					.padding(5.dp),
				dateState.value
			)

			when (displayState.value) {
				DisplayState.Time -> {
					TimeMatrixDisplay(
						modifier = Modifier.align(Alignment.Center),
						color = ClockStyle.primaryColor,
						clock = clockState.value
					)
				}
				DisplayState.Weather -> {
					Text(
						modifier = Modifier.align(Alignment.Center),
						color = ClockStyle.primaryColor,
						text = "Weather"
					)
				}
			}

//			TimeDisplay(
//				modifier = Modifier
//					.align(Alignment.BottomEnd)
//					.offset(0.dp, 40.dp),
//				clock = clockState.value
//			)
		}

		LaunchedEffect(Unit) {
			application.loop { clock ->
				clockState.value = clock
				dateState.value = application.currentDate()
			}
		}
	}
}

@ExperimentalGraphicsApi
fun main() = application {
	Window(
		onCloseRequest = ::exitApplication,
		resizable = false,
		state = WindowState(
			width = 800.dp,
			height = 480.dp
		),
		title = "PiClock"
	) {
		Surface(color = ClockStyle.backgroundColor) {
			App()
		}
	}
}
