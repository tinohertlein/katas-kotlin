package dev.hertlein.kata.marsrover


class Rover {

    private val startingPosition = Position(Direction.N, Coordinate(0, 0))

    enum class Direction {
        N, S, E, W
    }

    data class Coordinate(val x: Int, val y: Int)

    data class Position(val direction: Direction, val coordinate: Coordinate) {

        override fun toString() = "${coordinate.x}:${coordinate.y}:$direction"
    }

    fun navigate(): String {
        return startingPosition.toString()
    }
}
