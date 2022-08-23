package dev.hertlein.kata.marsrover


class Rover {

    private val startingPosition = Position(Direction.N, Coordinate(0, 0))

    enum class Command {
        L, R, M
    }

    enum class Direction {
        N, S, E, W
    }

    data class Coordinate(val x: Int, val y: Int)

    data class Position(val direction: Direction, val coordinate: Coordinate) {

        override fun toString() = "${coordinate.x}:${coordinate.y}:$direction"
    }

    fun navigate(commandInput: String = ""): String {
        val commands = toCommands(commandInput)

        return commands.fold(startingPosition) { currentPosition, command ->
            when (command) {
                Command.M -> move(
                    currentPosition
                )
                Command.L -> turnLeft(currentPosition)
                Command.R -> turnRight(currentPosition)
            }
        }.toString()
    }

    fun toCommands(commandInput: String) = commandInput
        .split("")
        .filter { it.isNotEmpty() }
        .map { Command.valueOf(it.uppercase()) }

    private fun move(currentPosition: Position): Position {
        return when (currentPosition.direction) {
            Direction.N -> currentPosition.let {
                it.copy(coordinate = it.coordinate.copy(x = it.coordinate.x + 1))
            }
            else -> currentPosition
        }
    }

    private fun turnLeft(currentPosition: Position): Position {
        val mappings = mapOf(
            Direction.N to Direction.W,
            Direction.W to Direction.S,
            Direction.S to Direction.E,
            Direction.E to Direction.N,
        )

        return turn(mappings, currentPosition)
    }

    private fun turnRight(currentPosition: Position): Position {
        val mappings = mapOf(
            Direction.N to Direction.E,
            Direction.E to Direction.S,
            Direction.S to Direction.W,
            Direction.W to Direction.N
        )

        return turn(mappings, currentPosition)
    }

    private fun turn(
        mappings: Map<Direction, Direction>,
        currentPosition: Position
    ): Position {
        require(mappings.containsKey(currentPosition.direction)) { "Unknown Direction: ${currentPosition.direction}" }

        return currentPosition.copy(direction = mappings[currentPosition.direction]!!)
    }
}
