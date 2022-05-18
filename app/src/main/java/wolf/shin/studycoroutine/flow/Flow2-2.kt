package wolf.shin.studycoroutine.flow

import android.util.Log
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
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