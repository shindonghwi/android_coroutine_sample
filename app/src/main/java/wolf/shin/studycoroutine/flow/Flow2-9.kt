package wolf.shin.studycoroutine.flow

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.runBlocking

fun events() = (1..3).asFlow().onEach { delay(300) }

fun flowEvent1() = runBlocking {
    events()
        .onEach { event -> println("Event: $event") }
        .collect()
    println("Done")
}