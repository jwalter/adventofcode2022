fun main() {
    data class Tree(val height: Int, var visible: Boolean = false, val viewingDistances: MutableList<Int> = mutableListOf<Int>())

    fun parse(input: List<String>): List<List<Tree>> {
        return input.map { it.map { height -> Tree(height.digitToInt()) } }
    }

    fun setViewingDistance(tree: Tree, currentLos: MutableList<Tree>) {
        val los = currentLos.indexOfFirst { it.height >= tree.height }
        if (los == -1) {
            tree.viewingDistances.add(currentLos.size)
        } else {
            tree.viewingDistances.add(los + 1)
        }
    }

    fun markVisibleTrees(forest: List<List<Tree>>, start: Pair<Int, Int>, direction: Pair<Int, Int>, step: Pair<Int, Int>) {
        var (x, y) = start
        repeat(forest.size) {
            x = start.first + step.first * it
            y = start.second + step.second * it
            var currMax = -1
            var currentLos = mutableListOf<Tree>()
            repeat(forest.size) {
                val tree = forest[x][y]
                if (tree.height > currMax) {
                    tree.visible = true
                    currMax = tree.height
                }
                setViewingDistance(tree, currentLos)
                currentLos.add(0, tree)
                x += direction.first
                y += direction.second
            }
        }
    }

    fun part1(input: List<String>): Int {
        val forest = parse(input)
        val width = forest.size - 1
        markVisibleTrees(forest, 0 to 0, 1 to 0, 0 to 1)
        markVisibleTrees(forest, 0 to 0, 0 to 1, 1 to 0)
        markVisibleTrees(forest, width to width, 0 to -1, -1 to 0)
        markVisibleTrees(forest, width to width, -1 to 0, 0 to -1)
        return forest.sumOf { row -> row.filter { it.visible }.size }
    }

    fun part2(input: List<String>): Int {
        val forest = parse(input)
        val width = forest.size - 1
        markVisibleTrees(forest, 0 to 0, 1 to 0, 0 to 1)
        markVisibleTrees(forest, 0 to 0, 0 to 1, 1 to 0)
        markVisibleTrees(forest, width to width, 0 to -1, -1 to 0)
        markVisibleTrees(forest, width to width, -1 to 0, 0 to -1)
        val result = forest.map { row -> row.map { it.viewingDistances.reduce(Int::times) }.max() }.max()
        return result
    }

    val testInput = readInput("Day08_test")
    check(part1(testInput) == 21)
    check(part2(testInput) == 8)

    val input = readInput("Day08")
    println(part1(input))
    println(part2(input))
}