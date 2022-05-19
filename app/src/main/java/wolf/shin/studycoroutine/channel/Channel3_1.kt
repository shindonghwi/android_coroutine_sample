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

fun channelClose1() = runBlocking {
    val channel = Channel<Int>()

    // 아래와 같이 send, receive가 중단점인데 같은 스코프 내에 있으면 무한대기 상태에 빠지게된다.
    launch {
        for (x in 1..10){
            channel.send(x) // suspension point
        }
        channel.close() // 채널이 더이상 보낼 자료가 없다면 close를 이용해서 닫는다.
    }

    for(x in channel){ // for in 을 통해 채널 데이터를 반복적으로 receive 할 수 있다.
        println(x)
    }
    println("완료")

}