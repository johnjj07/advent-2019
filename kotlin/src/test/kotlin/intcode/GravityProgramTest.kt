package intcode

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

class GravityProgramTest : DescribeSpec({
    describe("execute") {
        data class TestCase(val memory: MutableList<Int>, val first: Int, val second: Int, val result: Int)
        val cases = listOf(
            TestCase(mutableListOf(1, 0, 0, 0, 99), 0, 0, 2),
            TestCase(mutableListOf(2, 3, 0, 3, 99), 3, 0, 2),
            TestCase(mutableListOf(2, 4, 4, 5, 99, 0), 4, 4, 2),
            TestCase(mutableListOf(1, 1, 1, 4, 99, 5, 6, 0, 99), 1, 1, 30)
        )
        cases.forEach { (memory, first, second, result) ->
            GravityProgram(memory).execute(first, second).shouldBe(Success(result))
        }
    }
})