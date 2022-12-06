fun main() {

    fun part1(input: List<String>): Int {
        return input[0].windowed(4, 1).indexOfFirst { it.toSet().size == 4 } + 4
    }

    fun part2(input: List<String>): Int {
        return input[0].windowed(14, 1).indexOfFirst { it.toSet().size == 14 } + 14
    }

    val testInput = readInput("Day06_test")
    check(part1(testInput) == 10)

    val input = readInput("Day06")
    println(part1(input))
    println(part2(input))
}