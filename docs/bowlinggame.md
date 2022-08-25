# The Bowling Game Kata

Credits for this Kata go to [Uncle Bob](http://www.butunclebob.com/ArticleS.UncleBob.TheBowlingGameKata)

## Situation

A bowling game consists of 10 frames. In each frame the player has two rolls to knock down 10 pins. The score for the
frame is the total number of pins knocked down, plus bonuses for strikes and spares.

A spare is when the player knocks down all 10 pins in two rolls. The bonus for that frame is the number of pins knocked
down by the next roll.

A strike is when the player knocks down all 10 pins on his first roll. The frame is then completed with a single roll.
The bonus for that frame is the value of the next two rolls.

In the tenth frame a player who rolls a spare or strike is allowed to roll the extra balls to complete the frame.
However, no more than three balls can be rolled in tenth frame.

## Task

Your task is to develop a calculator for bowling games.

Write a class `BowlingGame` that has two methods:

* `roll(pins: Int)`  is called each time the player rolls a ball. The argument is the number of pins knocked down.
* `score(): Int` returns the total score for that game.

### Examples

When scoring, “X” indicates a strike, “/” indicates a spare, “-” indicates a miss

* X X X X X X X X X X X X (12 rolls: 12 strikes) = 10 frames * 30 points = 300
* 9- 9- 9- 9- 9- 9- 9- 9- 9- 9- (20 rolls: 10 pairs of 9 and miss) = 10 frames * 9 points = 90
* 5/ 5/ 5/ 5/ 5/ 5/ 5/ 5/ 5/ 5/5 (21 rolls: 10 pairs of 5 and spare, with a final 5) = 10 frames * 15 points = 150
