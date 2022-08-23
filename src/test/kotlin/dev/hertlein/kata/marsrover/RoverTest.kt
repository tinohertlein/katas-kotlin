package dev.hertlein.kata.marsrover

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class RoverTest {

    @Test
    fun `should answer with world`() {
        assertThat(Rover().hello()).isEqualTo("World")
    }
}
