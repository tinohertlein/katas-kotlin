package dev.hertlein.katas.gameoflife


class GameOfLife(val grid: Grid) {

    data class Cell(val row: Int, val column: Int, val isAlive: Boolean) {
        fun isNeighbourOf(cell: Cell): Boolean {
            return this != cell
                    && (cell.row in this.row - 1..this.row + 1)
                    && (cell.column in this.column - 1..this.column + 1)
        }
    }

    data class Grid(val cells: List<Cell>)

    @Suppress("MagicNumber")
    fun play(): GameOfLife {
        val nextGenerationCells = grid.cells.map {
            val numberOfAliveNeighbours = countAliveNeighboursOf(it)

            when {
                !it.isAlive && numberOfAliveNeighbours == 3 -> it.copy(isAlive = true)
                it.isAlive && numberOfAliveNeighbours < 2 -> it.copy(isAlive = false)
                it.isAlive && numberOfAliveNeighbours > 3 -> it.copy(isAlive = false)
                else -> it
            }
        }

        return GameOfLife(Grid(nextGenerationCells))
    }

    private fun countAliveNeighboursOf(cell: Cell): Int = grid.cells
        .count { it.isNeighbourOf(cell) && it.isAlive }
}
