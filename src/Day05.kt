fun main() {
    data class Cmd(val from: Int, val to: Int, val count: Int)

    fun parseInput(input: List<String>): Pair<List<MutableList<String>>, List<Cmd>> {
        val stackRows = input.takeWhile { it.contains("[") }.map { it.chunked(4) }
        val stackCount = stackRows.maxOf { it.size }
        val stacks = (0 until stackCount).map { mutableListOf<String>() }
        stackRows.forEach { row ->
            row.forEachIndexed { idx, crate ->
                if (crate.isNotBlank()) {
                    stacks[idx].add(crate)
                }
            }
        }
        val instructions = input
            .dropWhile { !it.contains("move") }
            .map { it.split(" ") }
            .map { Cmd(it[3].toInt() - 1, it[5].toInt() - 1, it[1].toInt()) }
        return stacks to instructions
    }

    fun applyInstructions(
        stacks: List<MutableList<String>>,
        instructions: List<Cmd>,
        func: (MutableList<String>, MutableList<String>, Int) -> Unit
    ): String {
        instructions.forEach {
            func(stacks[it.from], stacks[it.to], it.count)
        }
        return stacks.map { it.first()[1] }.joinToString("")
    }

    fun part1(input: List<String>): String {
        val (stacks, instructions) = parseInput(input)
        return applyInstructions(stacks, instructions) { src, target, count ->
            repeat(count) {
                val crate = src.removeFirst()
                target.add(0, crate)
            }
        }
    }

    fun part2(input: List<String>): String {
        val (stacks, instructions) = parseInput(input)
        return applyInstructions(stacks, instructions) { src, target, count ->
            repeat(count) {
                val crate = src.removeFirst()
                target.add(it, crate)
            }
        }
    }

    val testInput = readInput("Day05_test")
    check(part1(testInput) == "CMZ")
    check(part2(testInput) == "MCD")

    val input = readInput("Day05")
    println(part1(input))
    println(part2(input))
}
