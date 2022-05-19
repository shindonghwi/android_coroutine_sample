package wolf.shin.studycoroutine.channel

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.runBlocking

fun CoroutineScope.produceNumbers() = produce<Int> {
    var x = 1
    while (true){
        send(x++)
    }
}

fun CoroutineScope.produceStringNumbers(numbers: ReceiveChannel<Int>): ReceiveChannel<String> = produce {
    numbers.consumeEach {
        send("$it !")
    }
}

fun channelPipeLine1() = runBlocking {
    val numbers = produceNumbers()
    var stringNumbers = produceStringNumbers(numbers) // 채널을 사용하여 다른 채널을 만들어내는 것이 파이프라인

    repeat(5){
        println(stringNumbers.receive())
    }

    println("완료")
    coroutineContext.cancelChildren()
}