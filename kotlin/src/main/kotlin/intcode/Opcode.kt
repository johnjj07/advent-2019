package intcode

enum class Opcode(val size: Int) {
    ADD(4), MUL(4), END(1), INVALID(0);

    companion object {
        fun of(code: Int): Opcode {
            return when (code) {
                1 -> ADD
                2 -> MUL
                99 -> END
                else -> INVALID
            }
        }
    }
}
