fun main() {
    fun Pair<Int, Int>.rangeContains(other: Pair<Int, Int>): Boolean {
        val result = this.first <= other.first && this.second >= other.second
        return result
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

    fun part1(input: List<String>): Int {
        val aa = input.map { it.split(",") }.map {
            it.map { it.split("-").map { it.toInt() }.let { p -> p.first() to p.last() } }.let {
                fullyContains(it.first(), it.last())
            }
        }.filter { it }

        return aa.count()
    }

    fun part2(input: List<String>): Int {
        val aa = input.map { it.split(",") }.map {
            it.map { it.split("-").map { it.toInt() }.let { p -> p.first() to p.last() } }.let {
                overlaps(it.first(), it.last())
            }
        }.filter { it }

        return aa.count()
    }

    val testInput = readInput("Day04_test")
    check(part1(testInput) == 2)
    check(part2(testInput) == 4)

    val input = readInput("Day04")
    println(part1(input))
    println(part2(input))
}
