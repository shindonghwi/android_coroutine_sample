package wolf.shin.studycoroutine.flow

import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking

fun flowFinallySimple1() = (1..3).asFlow()

fun flowFinally1() = runBlocking {
    flowFinallySimple1()
        .map {
            if (it > 2){
                throw IllegalStateException()
            }
            it + 1
        }
        .catch { println("error: $it") }
        .onCompletion { println("Done") }
        .collect { value -> println(value) }
}

fun flowFinallySimple2() = flow{
    emit(1)
//    throw IllegalStateException()
}

fun flowFinally2() = runBlocking {
    flowFinallySimple2()
        .onCompletion { cause -> if (cause != null) println("Flow Error") else println("Flow Completed") }
        .catch { e -> println("Caught exception: $e") }
        .collect { value -> println(value) }
}
