import org.gradle.api.tasks.testing.logging.TestLogEvent
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.7.21"
    kotlin("plugin.serialization") version "1.7.21"
    id("io.gitlab.arturbosch.detekt").version("1.22.0")
}

group = "dev.hertlein"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.4.1")
    implementation("com.google.guava:guava:31.1-jre")

    testImplementation(kotlin("test"))
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.9.1")
    testImplementation("org.junit.jupiter:junit-jupiter-params:5.9.1")
    testImplementation("org.assertj:assertj-core:3.23.1")
    testImplementation("io.mockk:mockk:1.13.2")

}

tasks.test {
    useJUnitPlatform()
    testLogging.events = setOf(
        TestLogEvent.PASSED, TestLogEvent.SKIPPED, TestLogEvent.FAILED,
        TestLogEvent.STANDARD_OUT, TestLogEvent.STANDARD_ERROR
    )
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "17"
}
