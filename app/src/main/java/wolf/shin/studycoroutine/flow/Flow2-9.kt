package wolf.shin.studycoroutine.flow

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.runBlocking

fun events() = (1..3).asFlow().onEach { delay(300) }

fun flowEvent1() = runBlocking {

    // onEach는 collect가 실행되어야 동작한다.
    // 이런식으로 동작하게된다면 main에서 Ui의 멈춤이 발생하여 ANR이 발생할 가능성이 매우높다

    events()
        .onEach { event -> println("Event: $event") }
        .collect()
    println("Done")
}
fun flowEvent2() = runBlocking { // this, 코루틴 스코프, 코루틴
    events()
        .onEach { event -> println("Event: $event : $this") }
        .launchIn(this) // 코루틴 스코프 // 새로운 코루틴을 만들어낸다.
    println("Done")
}