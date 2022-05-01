package wolf.shin.studycoroutine

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.actor
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import wolf.shin.studycoroutine.ui.theme.StudyCoroutineTheme
import java.util.concurrent.atomic.AtomicInteger
import kotlin.random.Random
import kotlin.system.measureTimeMillis

val TAG = "CoroutineStudy"

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalStdlibApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StudyCoroutineTheme {
                main()
            }
        }
    }

    suspend fun massiveRun(action: suspend () -> Unit){
        val n = 100 // 시작할 코루틴의 갯수
        val k = 1000 // 코루틴 내에서 반복할 횟수

        val elapsed = measureTimeMillis {
            coroutineScope {
                repeat(n){
                    launch {
                        repeat(k) { action() }
                    }
                }
            }
        }
        println("$elapsed ms동안 ${n * k}개의 액션을 수행했다.")
    }

    fun CoroutineScope.counterActor() = actor<CounterMsg> {
        var counter = 0
        for (msg in channel){
            when(msg){
                is IncCounter -> counter++
                is GetCounter -> msg.response.complete(counter)
            }
        }
    }

    fun main() = runBlocking {
        val counter = counterActor()
        withContext(Dispatchers.Default){
            massiveRun {
                counter.send(IncCounter)
            }
        }

        val response = CompletableDeferred<Int>()
        counter.send(GetCounter(response))
        print("Counter: ${response.await()}")
        counter.close()
    }

    sealed class CounterMsg
    object IncCounter : CounterMsg()
    class GetCounter(val response: CompletableDeferred<Int>): CounterMsg()

}