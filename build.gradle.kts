import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.gradle.api.tasks.testing.logging.TestLogEvent

plugins {
    kotlin("jvm") version "1.5.10"
    id("io.gitlab.arturbosch.detekt").version("1.21.0")
}

group = "dev.hertlein.kata"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.9.0")
    testImplementation("org.junit.jupiter:junit-jupiter-params:5.9.0")
    testImplementation("org.assertj:assertj-core:3.23.1")
}

tasks.test {
    useJUnitPlatform()
    testLogging.events = setOf(
        TestLogEvent.PASSED, TestLogEvent.SKIPPED, TestLogEvent.FAILED,
        TestLogEvent.STANDARD_OUT, TestLogEvent.STANDARD_ERROR
    )
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "11"
}
