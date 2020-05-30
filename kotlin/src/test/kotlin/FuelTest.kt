import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

class FuelTest : DescribeSpec({
    describe("fuelRequired") {
        it("should calculate fuel needed for module") {
            listOf(
                12L to 2L,
                14L to 2L,
                1969L to 654L,
                100756L to 33583L
            )
                .forEach { (mass, fuel) -> fuelRequired(mass).shouldBe(fuel) }
        }
    }

    describe("fuelForFuel") {
        it("should determine the extra fuel needed for the fuel for a module") {
            listOf(
                1969L to 966L,
                100756L to 50346L
            )
                .forEach { (fuelMass, fuel) -> fuelForFuel(fuelMass).shouldBe(fuel) }
        }
    }
})