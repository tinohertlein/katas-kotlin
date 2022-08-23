package dev.hertlein.kata.marsrover

import dev.hertlein.kata.marsrover.Rover.Command
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

internal class RoverTest {

    private val rover = Rover()

    @Nested
    inner class Commands {

        @Test
        fun `should be converted if empty`() {
            val commands = rover.toCommands("")

            assertThat(commands).isEqualTo(emptyList<Command>())
        }

        @Test
        fun `should be converted if present`() {
            val commands = rover.toCommands("MLR")

            assertThat(commands).isEqualTo(listOf(Command.M, Command.L, Command.R))
        }
    }

    @Nested
    inner class Navigation {

        @Test
        fun `should start at initial position`() {
            val position = rover.navigate()

            assertThat(position).isEqualTo("0:0:N")
        }

        @Test
        fun `should move facing 'N'`() {
            val position = rover.navigate("MM")

            assertThat(position).isEqualTo("2:0:N")
        }

        @Test
        fun `should turn left facing 'N'`() {
            val position = rover.navigate("L")

            assertThat(position).isEqualTo("0:0:E")
        }

    }

}
