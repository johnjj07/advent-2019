package intcode

class GravityProgram(private val memory: MutableList<Int>) {
    fun execute(input1: Int, input2: Int): ExecutionResult {
        memory[1] = input1
        memory[2] = input2
        val mem = Memory(memory)
        mem.forEachRemaining { executeInstruction(it) }
        return validateExecution(mem)
    }

    private fun validateExecution(memory: Memory): ExecutionResult {
        return when (val i = memory.lastInstruction) {
            is TerminateWithError -> Failure(i.message)
            is Terminate -> Success(memory.solution())
            else -> Failure("Did not receive a termination code")
        }
    }

    private fun executeInstruction(instruction: Instruction) {
        when (instruction) {
            is Add -> onAddition(instruction)
            is Multiply -> onMultiplication(instruction)
            is TerminateWithError, Terminate -> Unit
        }
    }

    private fun onAddition(add: Add) {
        val result = memory[add.first] + memory[add.second]
        memory[add.position] = result
    }

    private fun onMultiplication(multiply: Multiply) {
        val result = memory[multiply.first] * memory[multiply.second]
        memory[multiply.position] = result
    }
}

sealed class ExecutionResult
data class Success(val result: Int) : ExecutionResult()
data class Failure(val message: String) : ExecutionResult()
