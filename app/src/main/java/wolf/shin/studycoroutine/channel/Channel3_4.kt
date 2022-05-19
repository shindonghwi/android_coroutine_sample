package wolf.shin.studycoroutine.channel

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun channelBuffer1() = runBlocking {
    val channel = Channel<Int>(10) // 채널의 버퍼 갯수가 10

    launch {
        for (x in 1..20){
            println("$x 전송중")
            channel.send(x) // 받든 안받든 채널로 계속보낸다.
        }
        channel.close()
    }

    channel.consumeEach {
        println("$it 수신")
        delay(100L)
    }
}