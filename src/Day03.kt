fun main() {

    fun codeScore(code: Int): Int {
        return if (code >= 98) {
            code - 96
        } else {
            code - 38
        }
    }

    fun score(pair: List<String>): Int {
        val common = pair.drop(1).fold(pair.first()) { acc, s ->
            s.filter { acc.contains(it) }
        }
        val i = common.toSet().map { codeScore(it.code) }
        return i.sum()
    }

    fun part1(input: List<String>): Int {
        return input
            .map { it.substring(0, it.length / 2) to it.substring(it.length / 2) }.sumOf { score(it.toList()) }
    }

    fun part2(input: List<String>): Int {
        var first = ""
        var second = ""
        val groups = input.mapIndexedNotNull { index, s ->
            when (index % 3) {
                0 -> {
                    first = s
                    null
                }

                1 -> {
                    second = s
                    null
                }

                else -> listOf(first, second, s)
            }
        }.sumOf { score(it) }
        return groups
    }

    val testInput = readInput("Day03_test")
    check(part1(testInput) == 157)
    check(part2(testInput) == 70)

    val input = readInput("Day03")
    println(part1(input))
    println(part2(input))
}
