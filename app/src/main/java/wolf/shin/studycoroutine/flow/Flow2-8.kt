package wolf.shin.studycoroutine.flow

import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking

fun flowFinallySimple1() = (1..3).asFlow()

fun flowFinally1() = runBlocking {
    flowFinallySimple1()
        .onCompletion { println("Done") }
        .collect { value -> println(value) }
}
