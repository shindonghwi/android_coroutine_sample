package wolf.shin.studycoroutine.flow

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

fun log(msg: String) = println("[${Thread.currentThread().name}] $msg")

fun simple() = flow {
    log("Flow 시작")
    for (i in 1..10) {
        emit(i)
    }
}

fun simpleWithContext() = flow {
    log("Flow 시작")
    withContext(Dispatchers.Default){ /** 플로우 내에서는 context를 변경 할 수 없어서 에러가난다. */
        for (i in 1..10) {
            emit(i)
        }
    }
}

fun main_flow_2_3() = runBlocking {
    launch(Dispatchers.IO) { // 코루틴 호출전에 Dispather를 설정했으므로 내부 스레드는 IO 스레드를 따른다.
        simple().collect { value -> log("${value}를 받음.") }
    }
}

fun flowSimpleWithContext() = runBlocking {
    launch(Dispatchers.IO) { // 코루틴 호출전에 Dispather를 설정했으므로 내부 스레드는 IO 스레드를 따른다.
        simpleWithContext().collect { value -> log("${value}를 받음.") }
    }
}