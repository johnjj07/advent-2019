package intcode

sealed class Instruction
data class Add(val first: Int, val second: Int, val position: Int) : Instruction()
data class Multiply(val first: Int, val second: Int, val position: Int) : Instruction()
data class TerminateWithError(val message: String) : Instruction()
object Terminate : Instruction()
