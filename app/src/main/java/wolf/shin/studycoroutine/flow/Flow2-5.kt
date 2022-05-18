package wolf.shin.studycoroutine.flow

import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.zip
import kotlinx.coroutines.runBlocking

fun flowZip() = runBlocking {
    val nums = (1..5).asFlow() // 1, 2, 3
    val strs = flowOf("일", "이", "삼") // 일, 이, 삼

    nums.zip(strs) { a, b ->
        "${a}는 $b"
    }.collect { println(it) }
}