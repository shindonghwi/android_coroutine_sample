package wolf.shin.studycoroutine.flow

import android.util.Log
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking
import wolf.shin.studycoroutine.TAG
import kotlin.random.Random


fun flowSomething(): Flow<Int> = flow{
    repeat(10){
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