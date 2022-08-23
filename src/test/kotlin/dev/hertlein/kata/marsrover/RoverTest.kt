package dev.hertlein.kata.marsrover

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

internal class RoverTest {

    private val rover = Rover()

    @Test
    fun `should start at initial position`() {
        val position = rover.navigate()

        assertThat(position).isEqualTo("0:0:N")
    }

    @ParameterizedTest
    @CsvSource("MM,0:2:N", "RM,1:0:E", "RRM,0:-1:S", "RRRM,-1:0:W")
    fun `should move`(input: String, output: String) {
        val position = rover.navigate(input)

        assertThat(position).isEqualTo(output)
    }

    @ParameterizedTest
    @CsvSource("L,W", "LL,S", "LLL,E", "LLLL,N")
    fun `should turn left`(input: String, output: String) {
        val position = rover.navigate(input)

        assertThat(position).isEqualTo("0:0:$output")
    }

    @ParameterizedTest
    @CsvSource("R,E", "RR,S", "RRR,W", "RRRR,N")
    fun `should turn right`(input: String, output: String) {
        val position = rover.navigate(input)

        assertThat(position).isEqualTo("0:0:$output")
    }

    @ParameterizedTest
    @CsvSource("MMMMMMMMMM,0:0:N", "LMMMMMMMMMM,0:0:W", "RMMMMMMMMMM,0:0:E", "RRMMMMMMMMMM,0:0:S")
    fun `should wrap around from N`(input: String, output: String) {
        val position = rover.navigate(input)

        assertThat(position).isEqualTo(output)
    }
}
