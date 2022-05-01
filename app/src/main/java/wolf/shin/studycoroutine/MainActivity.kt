package wolf.shin.studycoroutine

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import kotlinx.coroutines.*
import wolf.shin.studycoroutine.ui.theme.StudyCoroutineTheme
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

    @Volatile // 가시성 문제를 해결 할 수 있어도, 동시에 읽고 수정해서 생기는 문제는 해결 못한다.
    var counter = 0

    fun main() = runBlocking {
        withContext(Dispatchers.Default){
            massiveRun {
                counter++
            }
        }
        println("Counter = $counter")
    }

}