fun main() {

    fun valOf(v: String): Int {
        return when {
            "AX".contains(v) -> 1
            "BY".contains(v) -> 2
            "CZ".contains(v) -> 3
            else -> 0
        }
    }

    fun score(theirs: Int, mine: Int): Int {
        val outcome = when(mine - theirs) {
            1 -> 6
            -2 -> 6
            0 -> 3
            else -> 0
        }
        return outcome + mine
    }

    fun score(row: String): Int {
        val (theirs, mine) = row.split(" ").map { valOf(it) }
        return score(theirs, mine)
    }

    fun makeMove(theirs: Int, outcome: Int): Int {
        return when(outcome) {
            1 -> if (theirs == 1) 3 else theirs -1
            3 -> if (theirs == 3) 1 else theirs +1
            else -> theirs
        }
    }

    fun score2(row: String): Int {
        val (theirs, outcome) = row.split(" ").map { valOf(it) }
        val mine = makeMove(theirs, outcome)
        return score(theirs, mine)
    }
    fun part1(input: List<String>): Int {
        return input.sumOf { score(it) }
    }

    fun part2(input: List<String>): Int {
        val scores = input.map { score2(it) }
        return scores.sum()
    }

    val testInput = readInput("Day02_test")
    check(part1(testInput) == 15)
    check(part2(testInput) == 12)

    val input = readInput("Day02")
    println(part1(input))
    println(part2(input))
}
