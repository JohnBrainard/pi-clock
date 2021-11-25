package dev.johnbrainard.clock

import kotlinx.coroutines.delay
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter
import java.time.Clock as JavaClock

class ClockApplication(
	private val systemClock: JavaClock = JavaClock.systemDefaultZone(),
	private val dateFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern(
		"EEEE, MMMM dd, YYYY"
	)
) {

	fun currentClock(): Clock {
		val now = OffsetDateTime.now(systemClock)
		return Clock(
			hour = String.format("%02d", now.hour.mod(12)),
			minute = String.format("%02d", now.minute),
			second = String.format("%02d", now.second),
			separatorOn = now.second.mod(2) == 0
		)
	}

	fun currentDate(): String {
		val now = OffsetDateTime.now(systemClock)
		return now.format(dateFormatter)
	}

	suspend fun loop(block: (clock: Clock) -> Unit) {
		while (true) {
			delay(33)
			block(currentClock())
		}
	}
}
