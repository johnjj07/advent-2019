import intcode.GravityProgram
import java.io.File

fun main() {
    val solutions = listOf(
        dayOne(),
        dayTwo()
    )
    solutions.forEach {
        println("Day ${it.day} || Part 1: ${it.partOne} || Part 2: ${it.partTwo}")
    }
}

private fun dayOne(): Solution {
    val moduleFuel = loadFile(1)
        .useLines {
            it.map { mass -> fuelRequired(mass.toLong()) }.toList()
        }
    val extraFuel = moduleFuel.map(::fuelForFuel)
    val day1 = moduleFuel.sum()
    val day2 = day1 + extraFuel.sum()
    return Solution(1, day1, day2)
}

private fun dayTwo(): Solution {
    val codes = loadFile(2)
            .useLines { input ->
                val l = input.map { it.split(",") }.toList()
                    .flatten()
                l.map { it.toInt() }
            }.toMutableList()
        .also { it[1] = 12; it[2] = 2}
    val part1 = GravityProgram(codes).execute(12, 2)
    return Solution(2, part1, part1)
}

private val resourceReader = object {}

fun loadFile(day: Int): File {
    return File(resourceReader.javaClass.getResource("day_$day.txt").file)
}