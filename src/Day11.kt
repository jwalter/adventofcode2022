import java.math.BigInteger

class Monkey(
    val items: MutableList<BigInteger>,
    val operation: (BigInteger) -> BigInteger,
    val testValue: Int,
    val router: (Boolean) -> Int
) {
     var inspections: Long = 0
    fun play() {
        inspections += items.size
        items.forEach {
            println(" Inspect $it")
            var i = operation(it)
            println("  Worry level updated to $i")
            i = i.mod(divideBy.toBigInteger())
            println("  Divided by three to $i")
            val divisible = i.mod(testValue.toBigInteger()) == 0.toBigInteger()
            val n = router(divisible)
            println("  Item with worry level $i thrown to monkey $n")
            monkeys[n].accept(i)
        }
        items.clear()
    }

    fun accept(item: BigInteger) = items.add(item)
}

var monkeys = mutableListOf<Monkey>()
var divideBy = 3
fun main() {

    fun part1(input: List<String>): BigInteger {
        monkeys = mutableListOf(
            Monkey(
                mutableListOf(56, 56, 92, 65, 71, 61, 79).big(),
                { it * 7.toBigInteger() },
                3,
                { if (it) 3 else 7 }
            ),
            Monkey(
                mutableListOf(61,85).big(),
                { it + 5.toBigInteger() },
                11,
                { if (it) 6 else 4 }
            ),
            Monkey(
                mutableListOf(54, 96, 82, 78, 69).big(),
                { it * it },
                7,
                { if (it) 0 else 7 }
            ),
            Monkey(
                mutableListOf(57, 59, 65, 95).big(),
                { it + 4.toBigInteger() },
                2,
                { if (it) 5 else 1 }
            ),
            Monkey(
                mutableListOf(62, 67, 80).big(),
                { it * 17.toBigInteger() },
                19,
                { if (it) 2 else 6 }
            ),
            Monkey(
                mutableListOf(91).big(),
                { it + 7.toBigInteger() },
                5,
                { if (it) 1 else 4 }
            ),
            Monkey(
                mutableListOf(79, 83, 64, 52, 77, 56, 63, 92).big(),
                { it + 6.toBigInteger() },
                17,
                { if (it) 2 else 0 }
            ),
            Monkey(
                mutableListOf(50, 97, 76, 96, 80, 56).big(),
                { it + 3.toBigInteger() },
                13,
                { if (it) 3 else 5 }
            )
        )
        divideBy = monkeys.map { it.testValue }.reduce(Math::multiplyExact)
        repeat(10000) { _ ->
            monkeys.forEachIndexed { idx, m ->
                println("Monkey $idx:")
                m.play()
            }
            println()
            monkeys.forEachIndexed { idx, m ->
                println("Monkey $idx: ${m.items}")
            }
            println()
        }
        monkeys.sortByDescending { it.inspections }
        return monkeys.take(2).map{it.inspections}.reduce(Math::multiplyExact).toBigInteger()
    }

    fun part2(input: List<String>): BigInteger {
        return 2.toBigInteger()
    }

    val testInput = readInput("Day11_test")
    //check(part1(testInput) == 10605)
    //check(part2(testInput) == 2)

    val input = readInput("Day11")
    println(part1(input))
    //println(part2(input))
}

private fun List<Int>.big(): MutableList<BigInteger> {
    return this.map { it.toBigInteger() }.toMutableList()
}
