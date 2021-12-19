pluginManagement {
	plugins {
		kotlin("jvm").version(extra["kotlin.version"] as String)
		id("org.jetbrains.compose").version(extra["compose.version"] as String)
	}

	repositories {
		mavenCentral()
		google()
		gradlePluginPortal()
		maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
	}
}

rootProject.name = "pi-clock"
