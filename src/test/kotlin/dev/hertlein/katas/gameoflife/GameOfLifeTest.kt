package dev.hertlein.katas.gameoflife

import dev.hertlein.katas.gameoflife.GameOfLife.Cell
import dev.hertlein.katas.gameoflife.GameOfLife.Grid
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("GameOfLife")
internal class GameOfLifeTest {

    @Test
    fun `should handle dead cells only`() {
        val cells = listOf(
            Cell(0, 0, false),
            Cell(0, 1, false),
            Cell(1, 0, false),
            Cell(1, 1, false)
        )
        val startingGrid = Grid(cells)

        assertThat(GameOfLife(startingGrid).play().grid).isEqualTo(startingGrid)
    }

    @Test
    fun `should handle block`() {
        val cells = listOf(
            Cell(0, 0, true),
            Cell(0, 1, true),
            Cell(1, 0, true),
            Cell(1, 1, true)
        )

        assertThat(GameOfLife(Grid(cells)).play().grid).isEqualTo(Grid(cells))
    }

    @Test
    fun `should handle blinker`() {
        val cellsGeneration1 = listOf(
            Cell(0, 0, false),
            Cell(0, 1, false),
            Cell(0, 2, false),
            Cell(1, 0, true),
            Cell(1, 1, true),
            Cell(1, 2, true),
            Cell(2, 0, false),
            Cell(2, 1, false),
            Cell(2, 2, false)
        )
        val cellsGeneration2 = listOf(
            Cell(0, 0, false),
            Cell(0, 1, true),
            Cell(0, 2, false),
            Cell(1, 0, false),
            Cell(1, 1, true),
            Cell(1, 2, false),
            Cell(2, 0, false),
            Cell(2, 1, true),
            Cell(2, 2, false)
        )
        val cellsGeneration3 = cellsGeneration1.toList()

        val generation1Game = GameOfLife(Grid(cellsGeneration1))

        val generation2Game = generation1Game.play()
        assertThat(generation2Game.grid.cells).isEqualTo(cellsGeneration2)

        val generation3Game = generation2Game.play()
        assertThat(generation3Game.grid.cells).isEqualTo(cellsGeneration3)
    }
}
