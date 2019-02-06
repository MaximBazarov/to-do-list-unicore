package im.mks.myfirstapplication.command

class Command<T>(
    private val action: (T) -> Unit
) {

    fun executeWith(value: T) {
        action(value)
    }

    fun bound(value: T) = PlainCommand {
        executeWith(value)
    }
}

typealias PlainCommand = Command<Unit>

fun PlainCommand.execute() {
    executeWith(Unit)
}


