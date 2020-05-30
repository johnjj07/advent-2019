package intcode

class Memory(private val memory: MutableList<Int>) : Iterator<Instruction> {
    private var position = 0
    var lastInstruction: Instruction? = null
        private set

    fun solution() = memory.first()

    override fun hasNext(): Boolean {
        return position < memory.size && !(lastInstruction is Terminate || lastInstruction is TerminateWithError)
    }

    override fun next(): Instruction {
        val code = memory[position]
        val opcode = Opcode.of(code)
        val instruction = when (opcode) {
            Opcode.ADD -> extractInstruction(opcode) { (_, f, s, p) -> Add(f, s, p) }
            Opcode.MUL -> {
                extractInstruction(opcode) { (_, first, second, position) -> Multiply(first, second, position) }
            }
            Opcode.END -> Terminate
            Opcode.INVALID -> TerminateWithError("Invalid opcode provided: $code")
        }
        position += opcode.size
        lastInstruction = instruction
        return instruction
    }

    private fun extractInstruction(opcode: Opcode, apply: (MutableList<Int>) -> Instruction): Instruction {
        return if ((position + opcode.size) < memory.size) {
            apply(memory.subList(position, position + opcode.size))
        } else {
            TerminateWithError("")
        }
    }
}
