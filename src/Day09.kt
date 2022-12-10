import kotlin.math.absoluteValue

fun main() {
    data class Knot(var x: Int, var y: Int)

    val down = 0 to -1
    val up = 0 to 1
    val right = 1 to 0
    val left = -1 to 0
    val visited = mutableSetOf<Pair<Int, Int>>()

    fun moveInDirection(direction: Pair<Int, Int>, knots: List<Knot>) {
        val h = knots.first()
        h.x += direction.first
        h.y += direction.second
        knots.drop(1).forEachIndexed { index, knot ->
            val delta = knots[index].x - knot.x to knots[index].y - knot.y
            if (delta.first == 0 && delta.second.absoluteValue > 1) {
                knot.y += delta.second / 2
            } else if (delta.second == 0 && delta.first.absoluteValue > 1) {
                knot.x += delta.first / 2
            } else if (delta.first.absoluteValue + delta.second.absoluteValue > 2) {
                knot.x += if (delta.first.absoluteValue > 1) {
                    delta.first / delta.first.absoluteValue
                } else delta.first
                knot.y += if (delta.second.absoluteValue > 1) {
                    delta.second / delta.second.absoluteValue
                } else delta.second
            }

        }
        val t = knots.last()
        visited.add(t.x to t.y)
    }

    fun move(direction: String, knots: List<Knot>) {
        when (direction) {
            "D" -> moveInDirection(down, knots)
            "U" -> moveInDirection(up, knots)
            "R" -> moveInDirection(right, knots)
            "L" -> moveInDirection(left, knots)
        }
    }


    fun computeCoverage(input: List<String>, knots: List<Knot>) {
        val moves = input.map { it.take(1) to it.drop(2).toInt() }
        visited.clear()
        visited.add(0 to 0)
        val x =
            moves.forEach { move ->
                repeat(move.second) {
                    move(move.first, knots)
                }
            }

    }

    fun part1(input: List<String>): Int {
        val knots = listOf(Knot(0,0), Knot(0,0))
        computeCoverage(input, knots)
        return visited.size
    }

    fun part2(input: List<String>): Int {
        val knots = (0..9).map { Knot(0,0) }
        computeCoverage(input, knots)
        return visited.size
    }

    val testInput = readInput("Day09_test")
    check(part1(testInput) == 13)
    check(part2(testInput) == 1)

    val input = readInput("Day09")
    println(part1(input))
    println(part2(input))
}