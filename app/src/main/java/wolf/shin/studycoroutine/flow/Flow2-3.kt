package wolf.shin.studycoroutine.flow

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
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
    withContext(Dispatchers.Default) {
        /** 플로우 내에서는 context를 변경 할 수 없어서 에러가난다. */
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


fun simpleFlowOn() = flow {
    log("Flow 시작")
    for (i in 1..10) {
        emit(i)
        log("${i}를 방출.")
    } // 여기가 flowOn위에 위치하므로 upstream
}.map {
    it * 2 // 업 스트림 // Dispather.Default
}.flowOn(Dispatchers.Default) // flowOn 연산자는 upStream에 있는 대상은 어떤 context에서 호출되게 할 수있는지 결정한다. // flowOn의 위치에 따라 upstream이 결정된다.
    .map {
        it * 2 // 다운 스트림
    }

fun flowSimpleFlowOn() = runBlocking {
    simpleFlowOn().collect { value -> //여기는 flowOn 아래이기때문에 downStream
        log("${value}를 받았음.")
    }
}