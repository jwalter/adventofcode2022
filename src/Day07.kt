import kotlin.math.abs

fun main() {
    data class Dir(
        val name: String,
        val parent: Dir?,
        var ownSize: Int = 0,
        val children: MutableList<Dir> = mutableListOf(),
        var totalSize: Int = 0
    )

    fun parseInput(input: List<String>): Dir {
        val root = Dir("/", null)
        var cd = root
        input.drop(1).forEach {
            when {
                it.startsWith("\$ cd ..") -> cd = cd.parent!!
                it.startsWith("\$ cd") -> cd = cd.children.first { ch -> ch.name == it.substring(5) }
                it.startsWith("dir") -> cd.children.add(Dir(it.substring(4), cd))
                it.startsWith("\$ ls") -> {}
                else -> cd.ownSize += it.split(" ")[0].toInt()
            }
        }
        return root
    }

    fun flatten(l: List<Dir>): List<Dir> {
        return l.flatMap { flatten(it.children) } + l
    }

    fun computeTotalSize(d: Dir): Int {
        d.totalSize = d.ownSize + d.children.sumOf { computeTotalSize(it) }
        return d.totalSize
    }

    fun part1(input: List<String>): Int {
        val root = parseInput(input)
        val dirs = flatten(root.children)
        dirs.forEach { computeTotalSize(it) }
        val sumOf = dirs.filter { it.totalSize <= 100000 }.sumOf { it.totalSize }
        return sumOf
    }

    fun part2(input: List<String>): Int {
        val root = parseInput(input)
        val dirs = flatten(root.children) + root
        dirs.forEach { computeTotalSize(it) }
        val missingSpace = abs(40000000 - root.totalSize)
        return dirs.filter { it.totalSize > missingSpace }.minOf { it.totalSize }
    }

    val testInput = readInput("Day07_test")
    check(part1(testInput) == 95437)
    check(part2(testInput) == 24933642)

    val input = readInput("Day07")
    println(part1(input))
    println(part2(input))
}