# The Game Of Life Kata

## Situation

This Kata is about calculating the next generation of Conway’s Game Of Life, given any starting position.
See [Wikipedia] (http://en.wikipedia.org/wiki/Conway%27s_Game_of_Life) for more detailed information.

## Task

You start with a finite, two-dimensional grid of cells, where each cell is either alive or dead. No life can exist off the edges. When calculating the next generation of the grid, follow these rules:

1. Any live cell with fewer than two live neighbours dies, as if caused by underpopulation.
2. Any live cell with more than three live neighbours dies, as if by overcrowding.
3. Any live cell with two or three live neighbours lives on to the next generation.
4. Any dead cell with exactly three live neighbours becomes a live cell. 

### Input

The input to the program is a two-dimensional grid of cells with boolean values. A boolean value of true means the cell is alive. A boolean value of false means the cell is dead.

### Output

The output of the program is a two-dimensional grid of cells with boolean values. A boolean value of true means the cell is alive. A boolean value of false means the cell is dead.

### Examples

#### The Block

#### Cycle 1

|        |        |
|--------|--------|
| *true* | *true* |
| *true* | *true* |

#### Cycle 2

|        |        |
|--------|--------|
| *true* | *true* |
| *true* | *true* |

#### The Blinker

#### Cycle 1

|        |        |        |
|--------|--------|--------|
| false  | false  | false  |
| *true* | *true* | *true* |
| false  | false  | false  |

#### Cycle 2

|       |        |       |
|-------|--------|-------|
| false | *true* | false |
| false | *true* | false |
| false | *true* | false |

#### Cycle 3

|        |        |        |
|--------|--------|--------|
| false  | false  | false  |
| *true* | *true* | *true* |
| false  | false  | false  |

