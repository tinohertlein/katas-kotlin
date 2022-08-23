package dev.hertlein.kata.marsrover

const val DEFAULT_GRID_SIZE = 10

class Rover(
    private val startingPosition: Position = Position(Direction.N, Coordinate(0, 0)),
    private val grid: Grid = Grid(DEFAULT_GRID_SIZE, DEFAULT_GRID_SIZE),
    private val obstacles: List<Obstacle> = emptyList()
) {

    enum class Command {
        L, R, M
    }

    enum class Direction {
        N, S, E, W;

        companion object {
            val turnLeftMappings = mapOf(
                N to W,
                W to S,
                S to E,
                E to N,
            )
            val turnRightMappings = mapOf(
                N to E,
                E to S,
                S to W,
                W to N
            )
        }
    }

    data class Coordinate(val x: Int, val y: Int)

    data class Position(val direction: Direction, val coordinate: Coordinate, val isCollision: Boolean = false) {

        override fun toString() = "${if (isCollision) "Err:" else ""}${coordinate.x}:${coordinate.y}:$direction"
    }

    data class Grid(val dimX: Int, val dimY: Int)

    data class Obstacle(val coordinate: Coordinate)

    fun navigate(commandInput: String = ""): String =
        toCommands(commandInput)
            .fold(startingPosition) { currentPosition, command ->
                when (command) {
                    Command.M -> move(currentPosition)
                    Command.L -> turnLeft(currentPosition)
                    Command.R -> turnRight(currentPosition)
                }
            }
            .toString()

    private fun toCommands(commandInput: String) = commandInput
        .split("")
        .filter { it.isNotEmpty() }
        .map { Command.valueOf(it.uppercase()) }

    private fun move(currentPosition: Position): Position {
        val nextPosition = when (currentPosition.direction) {
            Direction.N -> currentPosition.let {
                it.copy(coordinate = it.coordinate.copy(y = it.coordinate.y + 1))
            }
            Direction.E -> currentPosition.let {
                it.copy(coordinate = it.coordinate.copy(x = it.coordinate.x + 1))
            }
            Direction.S -> currentPosition.let {
                it.copy(coordinate = it.coordinate.copy(y = it.coordinate.y - 1))
            }
            Direction.W -> currentPosition.let {
                it.copy(coordinate = it.coordinate.copy(x = it.coordinate.x - 1))
            }
        }

        return detectCollision(currentPosition, wrapAround(nextPosition))
    }

    private fun wrapAround(currentPosition: Position): Position = currentPosition.let {
        return it.copy(
            coordinate = it.coordinate.copy(
                x = it.coordinate.x % grid.dimX,
                y = it.coordinate.y % grid.dimY
            )
        )
    }

    private fun detectCollision(currentPosition: Position, nextPosition: Position): Position {
        return if (obstacles.map { it.coordinate }.contains(nextPosition.coordinate)) {
            currentPosition.copy(isCollision = true)
        } else {
            nextPosition
        }
    }

    private fun turnLeft(currentPosition: Position): Position = turn(Direction.turnLeftMappings, currentPosition)

    private fun turnRight(currentPosition: Position): Position = turn(Direction.turnRightMappings, currentPosition)

    private fun turn(
        mappings: Map<Direction, Direction>,
        currentPosition: Position
    ): Position {
        require(mappings.containsKey(currentPosition.direction)) { "Unknown Direction: ${currentPosition.direction}" }

        return currentPosition.copy(direction = mappings[currentPosition.direction]!!)
    }
}
