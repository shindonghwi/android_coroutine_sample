package wolf.shin.studycoroutine.channel

import kotlinx.coroutines.channels.BufferOverflow
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
fun channelBuffer2() = runBlocking {
    val channel = Channel<Int>(Channel.RENDEZVOUS) // 0
//    val channel = Channel<Int>(Channel.UNLIMITED) // 무제한으로 설정 ( 메모리가 부족하면 runtime Error )
//    val channel = Channel<Int>(Channel.CONFLATED) // 오래된 값이 지워짐 ( 처리하지 못하는 값은 버린다. )
//    val channel = Channel<Int>(Channel.BUFFERED) // 64 개 버퍼 , 오버플로우엔 suspend 65개라면 잠이들게된다.



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
fun channelBuffer3() = runBlocking {
    val channel = Channel<Int>(2, BufferOverflow.DROP_OLDEST) // 예전 데이터를 지운다.
//    val channel = Channel<Int>(2, BufferOverflow.DROP_LATEST) // 새 데이터를 지운다.
//    val channel = Channel<Int>(2, BufferOverflow.SUSPEND) // 잠이 들었다가 꺠어난다.

    launch {
        for (x in 1..50){
            channel.send(x)
        }
        channel.close()
    }

    delay(500L)

    channel.consumeEach {
        println("$it 수신")
        delay(100L)
    }
    println("완료")
}