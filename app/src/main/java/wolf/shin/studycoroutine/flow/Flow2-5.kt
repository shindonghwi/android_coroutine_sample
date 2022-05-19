package wolf.shin.studycoroutine.flow

import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking

fun flowZip() = runBlocking {
    val nums = (1..5).asFlow() // 1, 2, 3
    val strs = flowOf("일", "이", "삼") // 일, 이, 삼

    nums.zip(strs) { a, b ->
        "${a}는 $b"
    }.collect { println(it) }
}

fun flowCombine() = runBlocking { // 양쪽의 데이터를 같은 시점에 묶지않고, 한쪽이 갱신되면 새로 묶어 데이터를 만드는 연산자
    val nums = (1..5).asFlow() // 1, 2, 3
    val strs = flowOf("일", "이", "삼") // 일, 이, 삼

    nums.combine(strs) { a, b ->
        "${a}는 $b"
    }.collect { println(it) }
}
