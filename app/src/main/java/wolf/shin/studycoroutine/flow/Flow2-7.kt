package wolf.shin.studycoroutine.flow

import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking

fun flowTryCatchSimple() = flow{
    for (i in 1..3){
        println("Emitting $i")
        emit(i)
    }
}

fun flowTryCatch() = runBlocking {
    try{
        flowTryCatchSimple().collect { value ->
            println(value)
            check(value <= 1) { "Collected $value"}
        }
    }catch(e: Exception){
        println("Caught ${e.message}")
    }
}

fun flowTryCatchSimple1() = flow{
    for (i in 1..3){
        println("Emitting $i")
        emit(i)
    }
}.map { value ->
    check(value <= 1){ "Crashed on $value"}
    "string $value"
}

fun flowTryCatch1() = runBlocking {
    try{
        flowTryCatchSimple1().collect { value ->
            println(value)
        }
    }catch(e: Exception){
        println("Caught ${e.message}")
    }
}
