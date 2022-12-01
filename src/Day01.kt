fun main() {
    fun sumOfEachElf(input: List<String>): MutableList<Int> {
        val res = input.fold(mutableListOf<Int>()) { acc, value ->
            if (value.isBlank() || acc.isEmpty()) {
                acc.add(0)
            } else {
                acc.add(acc.removeLast() + value.toInt())
            }
            acc
        }
        return res
    }

    fun part1(input: List<String>): Int {
        val res = sumOfEachElf(input)
        return res.max()
    }

    fun part2(input: List<String>): Int {
        val sums = sumOfEachElf(input)
        sums.sortDescending()
        return sums.take(3).sum()
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01_test")
    check(part1(testInput) == 24000)

    val input = readInput("Day01")
    println(part1(input))
    println(part2(input))
}
