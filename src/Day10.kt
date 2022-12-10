fun main() {

    fun part1(input: List<String>): Int {
        var x = 1
        val result = input.flatMap {
            if (it == "noop") {
                listOf(x)
            } else {
                val curr = x
                x += it.drop(5).toInt()
                listOf(curr, x)
            }
        }
        val a = result.drop(19).chunked(40)
        val v = a.mapIndexed { idx, c ->
            if (c.size == 40) {
                c.last() * ((idx + 1) * 40 + 20)
            } else {
                0
            }
        }
        return v.sum() + result[19] * 20
    }

    fun updateScreen(drawIndex: Int, spritePosition: Int) {
        if (drawIndex % 40 in (spritePosition - 1)..(spritePosition + 1)) {
            print("#")
        } else {
            print(".")
        }
        if ((drawIndex + 1) % 40 == 0) {
            println()
        }
    }

    fun part2(input: List<String>): Int {
        var spritePosition = 1
        var drawIndex = 0
        input.forEach {
            updateScreen(drawIndex++, spritePosition)
            if (it != "noop") {
                updateScreen(drawIndex++, spritePosition)
                spritePosition += it.drop(5).toInt()
            }
        }
        return 2
    }

    val testInput = readInput("Day10_test")
    check(part1(testInput) == 13140)
    check(part2(testInput) == 2)

    val input = readInput("Day10")
    println(part1(input))
    println(part2(input))
}