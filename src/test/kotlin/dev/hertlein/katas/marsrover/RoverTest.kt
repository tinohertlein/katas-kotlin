package dev.hertlein.katas.marsrover

import dev.hertlein.katas.marsrover.Rover.Coordinate
import dev.hertlein.katas.marsrover.Rover.Obstacle
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

internal class RoverTest {

    private val rover = Rover()

    @Nested
    inner class UnitTests {

        @Test
        fun `should start at initial position`() {
            assertThat(rover.navigate()).isEqualTo("0:0:N")
        }

        @ParameterizedTest
        @CsvSource("MM,0:2:N", "RM,1:0:E", "RRM,0:-1:S", "RRRM,-1:0:W")
        fun `should move`(commands: String, expectedFinalPosition: String) {
            assertThat(rover.navigate(commands)).isEqualTo(expectedFinalPosition)
        }

        @ParameterizedTest
        @CsvSource("L,W", "LL,S", "LLL,E", "LLLL,N")
        fun `should turn left`(commands: String, expectedFinalPosition: String) {
            assertThat(rover.navigate(commands)).isEqualTo("0:0:$expectedFinalPosition")
        }

        @ParameterizedTest
        @CsvSource("R,E", "RR,S", "RRR,W", "RRRR,N")
        fun `should turn right`(commands: String, expectedFinalPosition: String) {
            assertThat(rover.navigate(commands)).isEqualTo("0:0:$expectedFinalPosition")
        }

        @ParameterizedTest
        @CsvSource("MMMMMMMMMM,0:0:N", "LMMMMMMMMMM,0:0:W", "RMMMMMMMMMM,0:0:E", "RRMMMMMMMMMM,0:0:S")
        fun `should wrap around from N`(commands: String, expectedFinalPosition: String) {
            assertThat(rover.navigate(commands)).isEqualTo(expectedFinalPosition)
        }
    }

    @Nested
    inner class AcceptanceTests {

        @Test
        fun `should navigate with commands given in example #1`() {
            assertThat(rover.navigate("")).isEqualTo("0:0:N")
        }

        @ParameterizedTest
        @CsvSource("MMRMMLM,2:3:N", "MMMMMMMMMM,0:0:N")
        fun `should navigate with commands given in example #2 & #3`(commands: String, expectedFinalPosition: String) {
            assertThat(rover.navigate(commands)).isEqualTo(expectedFinalPosition)
        }

        @Test
        fun `should navigate with commands given in example #4`() {
            assertThat(
                Rover(obstacles = listOf(Obstacle(Coordinate(0, 3))))
                    .navigate("MMMM")
            )
                .isEqualTo("Err:0:2:N")
        }
    }
}
