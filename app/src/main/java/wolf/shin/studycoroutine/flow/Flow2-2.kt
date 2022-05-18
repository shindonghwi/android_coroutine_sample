package wolf.shin.studycoroutine.flow

import android.util.Log
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking
import wolf.shin.studycoroutine.TAG
import kotlin.random.Random


fun flowSomething(): Flow<Int> = flow {
    repeat(10) {
        emit(Random.nextInt(0, 500))
        kotlinx.coroutines.delay(10L)
    }
}

fun main_flow_2_2() = runBlocking {
    flowSomething().map {
        "$it $it"
    }.collect { value -> Log.d(TAG, "main_flow_2_2: $value") }
}

fun flowFilter() = runBlocking {
    (1..20).asFlow().filter { it % 2 == 0 }.collect { Log.d(TAG, "flowFilter: $it") }
}

fun flowFilterNot() = runBlocking {
    (1..20).asFlow().filterNot { it % 2 == 0 }.collect { Log.d(TAG, "flowFilter: $it") }
}

fun flowMapAndFilterNot() = runBlocking {
    (1..20).asFlow().filterNot { it % 2 == 0 }.map { it * 3 }.collect { Log.d(TAG, "flowFilter: $it") }
}

suspend fun someCalc(i: Int): Int {
    delay(100L)
    return i * 2
}

fun flowTransform() = runBlocking {
    (1..20).asFlow().transform {
        emit(it) // 1 100ms 2 100ms 3
        emit(someCalc(it)) // 2 4
    }.collect { // 1 2 2 4 3 ...
        Log.d(TAG, "flowTransform: $it")
    }
}

fun flowTake() = runBlocking {
    (1..20).asFlow().transform {
        emit(it) // 1 100ms 2 100ms 3
        emit(someCalc(it)) // 2 4
    }.take(5)
        .collect { // 1 2 2 4 3 ...
            Log.d(TAG, "flowTransform: $it")
        }
}

fun flowTakeWhile() = runBlocking {
    (1..20).asFlow().transform {
        emit(it) // 1 100ms 2 100ms 3
        emit(someCalc(it)) // 2 4
    }.takeWhile { it > 10 }
        .collect { // 1 2 2 4 3 ...
            Log.d(TAG, "flowTransform: $it")
        }
}

fun flowDrop() = runBlocking {
    (1..20).asFlow().transform {
        emit(it) // 1 100ms 2 100ms 3
        emit(someCalc(it)) // 2 4
    }.drop(5) // 처음 5개를 버린다
        .collect { // 1 2 2 4 3 ...
            Log.d(TAG, "flowTransform: $it")
        }
}

fun flowDropWhile() = runBlocking {
    (1..20).asFlow().transform {
        emit(it) // 1 100ms 2 100ms 3
        emit(someCalc(it)) // 2 4
    }.dropWhile { it > 10 } // 10이하 값을 버린다
        .collect { // 1 2 2 4 3 ...
            Log.d(TAG, "flowDropWhile: $it")
        }
}

fun flowReduce() = runBlocking { // 누적계산 reduce
    val value = (1..10).asFlow().reduce { a, b -> // a => 1, b => 2 . a => 3, b= 3
        a + b
    }
    Log.d(TAG, "flowReduce: $value")
}

fun flowFold() = runBlocking { // 누적계산 reduce, 다만 초기값이 존재한다, 초기값이 a에 할당
    val value = (1..10).asFlow().fold(10){ a, b ->
        a + b
    }
    Log.d(TAG, "flowReduce: $value")
}