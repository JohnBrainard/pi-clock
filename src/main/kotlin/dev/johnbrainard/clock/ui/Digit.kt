package dev.johnbrainard.clock.ui

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ExperimentalGraphicsApi
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import dev.johnbrainard.clock.Clock

const val COLON = 10

val threeByFiveDigits = arrayOf(
	arrayOf(
		1, 1, 1,
		1, 0, 1,
		1, 0, 1,
		1, 0, 1,
		1, 1, 1
	),
	arrayOf(
		1, 1, 0,
		0, 1, 0,
		0, 1, 0,
		0, 1, 0,
		1, 1, 1
	),
	arrayOf(
		1, 1, 1,
		0, 0, 1,
		1, 1, 1,
		1, 0, 0,
		1, 1, 1,
	),
	arrayOf(
		1, 1, 1,
		0, 0, 1,
		0, 1, 1,
		0, 0, 1,
		1, 1, 1
	),
	arrayOf(
		1, 0, 1,
		1, 0, 1,
		1, 1, 1,
		0, 0, 1,
		0, 0, 1
	),
	arrayOf(
		1, 1, 1,
		1, 0, 0,
		1, 1, 1,
		0, 0, 1,
		1, 1, 1
	),
	arrayOf(
		1, 1, 1,
		1, 0, 0,
		1, 1, 1,
		1, 0, 1,
		1, 1, 1
	),
	arrayOf(
		1, 1, 1,
		0, 0, 1,
		0, 1, 0,
		1, 0, 0,
		1, 0, 0
	),
	arrayOf(
		1, 1, 1,
		1, 0, 1,
		1, 1, 1,
		1, 0, 1,
		1, 1, 1
	),
	arrayOf(
		1, 1, 1,
		1, 0, 1,
		1, 1, 1,
		0, 0, 1,
		0, 0, 1
	),
	arrayOf(
		0, 0, 0,
		0, 1, 0,
		0, 0, 0,
		0, 1, 0,
		0, 0, 0
	)
)

@Composable
fun Digit(
	modifier: Modifier = Modifier,
	color: Color = Color.Green,
	digit: Int
) {
	val dots = threeByFiveDigits[digit]
	Canvas(modifier = modifier) {
		val width = size.width / 3
		val height = size.height / 5

		for (x in 0 until 3) {
			for (y in 0 until 5) {
				drawRect(
					color = if (dots[x + y * 3] == 1) {
						color
					} else {
						Color(16, 16, 16)
					},
					topLeft = Offset(x * width, y * height),
					size = Size(width, height)
				)
				drawRect(
					color = Color.Black,
					style = Stroke(2f, miter = 4f),
					topLeft = Offset(x * width, y * height),
					size = Size(width, height)
				)
			}
		}
	}
}

@ExperimentalGraphicsApi
@Composable
fun TimeMatrixDisplay(
	modifier: Modifier = Modifier,
	color: Color = Color.Green,
	space: Dp = 8.dp,
	clock: Clock
) {
	Row(modifier = modifier) {
		val dotSize = 40f

		Digit(
			modifier = Modifier.width((dotSize * 3).dp).height((dotSize * 5).dp),
			color = color,
			digit = clock.hour[0].digitToInt()
		)
		Spacer(modifier = Modifier.width(space))
		Digit(
			modifier = Modifier.width((dotSize * 3).dp).height((dotSize * 5).dp),
			color = color,
			digit = clock.hour[1].digitToInt()
		)
		Spacer(modifier = Modifier.width(space))

		Digit(
			modifier = Modifier.width((dotSize * 3).dp).height((dotSize * 5).dp),
			color = if (clock.separatorOn) color else Color.Transparent,
			digit = COLON
		)

		Spacer(modifier = Modifier.width(space))
		Digit(
			modifier = Modifier.width((dotSize * 3).dp).height((dotSize * 5).dp),
			color = color,
			digit = clock.minute[0].digitToInt()
		)
		Spacer(modifier = Modifier.width(space))
		Digit(
			modifier = Modifier.width((dotSize * 3).dp).height((dotSize * 5).dp),
			color = color,
			digit = clock.minute[1].digitToInt()
		)
	}
}