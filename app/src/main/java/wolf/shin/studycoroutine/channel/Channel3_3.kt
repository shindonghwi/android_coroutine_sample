package wolf.shin.studycoroutine.channel

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun CoroutineScope.produceNumbers1() = produce<Int> {
    var x = 1
    while (true){
        send(x++)
    }
}

fun CoroutineScope.processNumber(id: Int, channel: ReceiveChannel<Int>) = launch {
    channel.consumeEach {
        println("$id 가 $it 를 받았습니다")
    }
}

fun FanInOut1() = runBlocking {
    val producer = produceNumbers1()
    repeat(5){
        processNumber(it, producer)
    }

    delay(1000L)
    producer.cancel()
}