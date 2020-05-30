import kotlin.math.floor

fun fuelRequired(mass: Long): Long {
    return floor(mass.div(3.0)).toLong() - 2
}

fun fuelForFuel(fuelMass: Long): Long {
    return fuelForFuel(fuelMass, 0)
}

private tailrec fun fuelForFuel(fuelMass: Long, carryOver: Long): Long {
    val f = fuelRequired(fuelMass)
    return when {
        f <= 0 -> carryOver
        else -> fuelForFuel(f, carryOver + f)
    }
}
