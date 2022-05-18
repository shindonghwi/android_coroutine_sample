package wolf.shin.studycoroutine.flow

import kotlinx.coroutines.flow.catch
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

fun flowTryCatchSimple2() = flow{
    for (i in 1..3){
        println("Emitting $i")
        emit(i)
    }
}.map { value ->
    check(value <= 1){ "Crashed on $value"}
    "string $value"
}

fun flowTryCatch2() = runBlocking {
    /** Flow 코드 블럭 내에서 예외처리 하는것은 예외 투명성을 어기는 것이다.
     * 그래서 catch연산자를 사용하여 처리하자. */
    flowTryCatchSimple2()
        .catch { e -> emit("Caught $e") }
        .collect { value -> println(value) }
}

fun flowTryCatchSimple3() = flow{
    for (i in 1..3){
        println("Emitting $i")
        emit(i)
    }
}

fun flowTryCatch3() = runBlocking {
    /** catch 의 투명성 - catch 연산자는 upstream에만 영향을 미치고, downstream에는 영향을 미치지 않는다. */
    flowTryCatchSimple3()
        .catch { e -> println("Caught $e") }
        .collect { value -> println(value) }
}
