package dev.hertlein.katas.bowlinggame

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

internal class BowlingGameTest {

    private val bowlingGame = BowlingGame()

    @Nested
    inner class UnitTests {

        @Test
        fun `should handle no hits`() {
            repeat(20) { bowlingGame.roll(0) }
            assertThat(bowlingGame.score()).isEqualTo(0)
        }

        @Test
        fun `should handle rolls without strikes or spares`() {
            repeat(20) { bowlingGame.roll(1) }

            assertThat(bowlingGame.score()).isEqualTo(20)
        }

        @Nested
        @DisplayName("Should handle strikes if")
        inner class Strikes {

            @Test
            fun `only strikes are rolled`() {
                repeat(12) { bowlingGame.roll(10) }

                assertThat(bowlingGame.score()).isEqualTo(300)
            }

            @Test
            fun `strike is rolled in first frame`() {
                bowlingGame.roll(10)
                bowlingGame.roll(8)
                bowlingGame.roll(8)
                repeat(16) { bowlingGame.roll(0) }

                assertThat(bowlingGame.score()).isEqualTo(42)
            }

            @Test
            fun `strike is rolled in last frame`() {
                repeat(18) { bowlingGame.roll(0) }
                bowlingGame.roll(10)
                bowlingGame.roll(8)
                bowlingGame.roll(8)

                assertThat(bowlingGame.score()).isEqualTo(26)
            }
        }

        @Nested
        @DisplayName("Should handle spares if")
        inner class Spares {

            @Test
            fun `only spares are rolled`() {
                repeat(21) { bowlingGame.roll(5) }

                assertThat(bowlingGame.score()).isEqualTo(150)
            }

            @Test
            fun `spare is rolled in first frame`() {
                bowlingGame.roll(5)
                bowlingGame.roll(5)
                bowlingGame.roll(8)
                repeat(17) { bowlingGame.roll(0) }

                assertThat(bowlingGame.score()).isEqualTo(26)
            }

            @Test
            fun `spare is rolled in last frame`() {
                repeat(18) { bowlingGame.roll(0) }
                bowlingGame.roll(5)
                bowlingGame.roll(5)
                bowlingGame.roll(8)

                assertThat(bowlingGame.score()).isEqualTo(18)
            }
        }
    }

    @Nested
    inner class AcceptanceTests {

        @Test
        fun `should calculate score as given in example #1`() {
            repeat(12) { bowlingGame.roll(10) }

            assertThat(bowlingGame.score()).isEqualTo(300)
        }

        @Test
        fun `should calculate score as given in example #2`() {
            repeat(10) {
                bowlingGame.roll(9)
                bowlingGame.roll(0)
            }

            assertThat(bowlingGame.score()).isEqualTo(90)
        }

        @Test
        fun `should calculate score as given in example #3`() {
            repeat(21) { bowlingGame.roll(5) }

            assertThat(bowlingGame.score()).isEqualTo(150)
        }
    }
}
