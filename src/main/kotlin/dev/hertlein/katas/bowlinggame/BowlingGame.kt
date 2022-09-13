package dev.hertlein.katas.bowlinggame

private const val FRAMES_PER_GAME = 10
private const val MAX_PINS_PER_FRAME = 10

class BowlingGame {

    private var state = State()

    fun roll(pins: Int) {
        state = state.roll(pins)
    }

    fun score(): Int {
        val scoreHeadRecursively = state.scoreHeadRecursively()
        val scoreTailRecursively = state.scoreTailRecursively()
        val scoreByLooping = state.scoreByLooping()

        check(allScoresAreEqual(scoreHeadRecursively, scoreTailRecursively, scoreByLooping)) {
            """There is a bug, as the scores differ: 
                | scoreHeadRecursively: $scoreHeadRecursively
                | scoreTailRecursively: $scoreTailRecursively
                | scoreByLooping: $scoreByLooping""".trimMargin()
        }

        return scoreHeadRecursively
    }

    private fun allScoresAreEqual(vararg results: Int) = results.toSet().size == 1

    private data class State(val pinsKnockedDown: List<Int> = emptyList()) {

        fun roll(pins: Int) = State(pinsKnockedDown + pins)

        fun scoreHeadRecursively(frameIndex: Int = 0, pins: List<Int> = this.pinsKnockedDown): Int {
            if (frameIndex < FRAMES_PER_GAME) {
                return if (isStrike(pins, 0)) {
                    val scoreInThisFrame = MAX_PINS_PER_FRAME + bonusForStrike(pins, 0)
                    scoreInThisFrame + scoreHeadRecursively(frameIndex + 1, pins.slice(1 until pins.size))

                } else if (isSpare(pins, 0)) {
                    val scoreInThisFrame = defaultScoreForFrame(pins, 0) + bonusForSpare(pins, 0)
                    scoreInThisFrame + scoreHeadRecursively(frameIndex + 1, pins.slice(2 until pins.size))

                } else {
                    val scoreInThisFrame = defaultScoreForFrame(pins, 0)
                    scoreInThisFrame + scoreHeadRecursively(frameIndex + 1, pins.slice(2 until pins.size))
                }
            }
            return 0
        }

        fun scoreTailRecursively(frameIndex: Int = 0, score: Int = 0, pins: List<Int> = this.pinsKnockedDown): Int {
            if (frameIndex < FRAMES_PER_GAME) {
                return if (isStrike(pins, 0)) {
                    val newScore = score + MAX_PINS_PER_FRAME + bonusForStrike(pins, 0)
                    scoreTailRecursively(frameIndex + 1, newScore, pins.slice(1 until pins.size))

                } else if (isSpare(pins, 0)) {
                    val newScore = score + defaultScoreForFrame(pins, 0) + bonusForSpare(pins, 0)
                    scoreTailRecursively(frameIndex + 1, newScore, pins.slice(2 until pins.size))

                } else {
                    val newScore = score + defaultScoreForFrame(pins, 0)
                    scoreTailRecursively(frameIndex + 1, newScore, pins.slice(2 until pins.size))
                }
            }

            return score
        }

        fun scoreByLooping(): Int {
            var score = 0
            var rollIndex = 0

            repeat(FRAMES_PER_GAME) {
                if (isStrike(pinsKnockedDown, rollIndex)) {
                    score += MAX_PINS_PER_FRAME
                    score += bonusForStrike(pinsKnockedDown, rollIndex)
                    rollIndex++
                } else if (isSpare(pinsKnockedDown, rollIndex)) {
                    score += defaultScoreForFrame(pinsKnockedDown, rollIndex)
                    score += bonusForSpare(pinsKnockedDown, rollIndex)
                    rollIndex += 2
                } else {
                    score += defaultScoreForFrame(pinsKnockedDown, rollIndex)
                    rollIndex += 2
                }
            }

            return score
        }

        private fun defaultScoreForFrame(pins: List<Int>, rollIndex: Int): Int = pins[rollIndex] + pins[rollIndex + 1]

        private fun isStrike(pins: List<Int>, rollIndex: Int) = pins[rollIndex] == MAX_PINS_PER_FRAME

        private fun bonusForStrike(pins: List<Int>, rollIndex: Int): Int = pins[rollIndex + 1] + pins[rollIndex + 2]

        private fun isSpare(pins: List<Int>, rollIndex: Int): Boolean =
            pins[rollIndex] + pins[rollIndex + 1] == MAX_PINS_PER_FRAME

        private fun bonusForSpare(pins: List<Int>, rollIndex: Int): Int = pins[rollIndex + 2]
    }
}
