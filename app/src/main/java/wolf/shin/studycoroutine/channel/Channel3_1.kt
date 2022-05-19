package wolf.shin.studycoroutine.channel

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun channel1() = runBlocking {
    val channel = Channel<Int>() // Int 타입이 돌아다니는 파이프라고 생각하자.

    launch {
        for (x in 1..10){
            channel.send(x) // 데이터를 송신한다.
        }
    }

    repeat(10){
        println(channel.receive()) // 데이터 수신부
    }

    println("완료")
}

fun channel2() = runBlocking {
    val channel = Channel<Int>() // try send,receive를 통해 보내고 받는쪽이 기다리지 않게한다.

    launch {
        for (x in 1..10){
            channel.trySend(x) // 데이터를 송신한다.
        }
    }

    repeat(10){
        println(channel.tryReceive()) // 데이터 수신부
    }

    println("완료")
}

fun channel3() = runBlocking {
    val channel = Channel<Int>()

    // 아래와 같이 send, receive가 중단점인데 같은 스코프 내에 있으면 무한대기 상태에 빠지게된다.
    launch {
        for (x in 1..10){
            channel.send(x) // suspension point
        }

        repeat(10){
            println(channel.receive()) // suspension point
        }

        println("완료")
    }
}