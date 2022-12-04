fun main() {
    fun Pair<Int, Int>.rangeContains(other: Pair<Int, Int>): Boolean {
        return first <= other.first && second >= other.second
    }

    fun fullyContains(a: Pair<Int, Int>, b: Pair<Int, Int>): Boolean {
        return a.rangeContains(b) || b.rangeContains(a)
    }

    fun overlaps(a: Pair<Int, Int>, b: Pair<Int, Int>): Boolean {
        val aRange = a.first..a.second
        val bRange = b.first..b.second
        return aRange.contains(b.first) ||
                aRange.contains(b.second) ||
                bRange.contains(a.first) ||
                bRange.contains(a.second)
    }

    fun countRangesMatching(input: List<String>, predicate: (Pair<Int, Int>, Pair<Int, Int>) -> Boolean): Int {
        return input
            .map { it.split(",") }
            .map {
                it
                    .map { assignment ->
                        assignment
                            .split("-")
                            .map(String::toInt)
                            .let { p -> p.first() to p.last() }
                    }.let { elfPairAssignment ->
                        predicate(elfPairAssignment.first(), elfPairAssignment.last())
                    }
            }.count { it }
    }

    fun part1(input: List<String>): Int {
        return countRangesMatching(input, ::fullyContains)
    }

    fun part2(input: List<String>): Int {
        return countRangesMatching(input, ::overlaps)
    }

    val testInput = readInput("Day04_test")
    check(part1(testInput) == 2)
    check(part2(testInput) == 4)

    val input = readInput("Day04")
    println(part1(input))
    println(part2(input))
}
