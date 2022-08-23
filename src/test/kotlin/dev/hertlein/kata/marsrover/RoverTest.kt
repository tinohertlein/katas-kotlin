package dev.hertlein.kata.marsrover

import dev.hertlein.kata.marsrover.Rover.Command
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

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

        @ParameterizedTest
        @CsvSource("L,E", "LL,S", "LLL,W", "LLLL,N")
        fun `should turn left`(input: String, output: String) {
            val position = rover.navigate(input)

            assertThat(position).isEqualTo("0:0:$output")
        }

        @ParameterizedTest
        @CsvSource("R,W", "RR,S", "RRR,E", "RRRR,N")
        fun `should turn right`(input: String, output: String) {
            val position = rover.navigate(input)

            assertThat(position).isEqualTo("0:0:$output")
        }

    }

}
