package dev.hertlein.kata.marsrover

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class RoverTest {

    private val rover = Rover()

    @Test
    fun `should start at initial position`() {
        val position = rover.navigate()

        assertThat(position).isEqualTo("0:0:N")
    }
}
