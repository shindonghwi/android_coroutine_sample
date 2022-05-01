package wolf.shin.studycoroutine

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import kotlinx.coroutines.*
import wolf.shin.studycoroutine.ui.theme.StudyCoroutineTheme
import kotlin.random.Random

val TAG = "CoroutineStudy"

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalStdlibApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StudyCoroutineTheme {

                runBlocking {

                    val scope = CoroutineScope(Dispatchers.IO + SupervisorJob() + ceh)

                    val job1 = scope.launch { printRandom1() }
                    val job2 = scope.launch { printRandom2() }

                    joinAll(job1, job2)

                }

            }
        }
    }

    suspend fun printRandom1() {
        delay(100L)
        println(Random.nextInt(0, 500))
    }

    suspend fun printRandom2() {
        delay(500L)
        throw ArithmeticException()
    }

    val ceh = CoroutineExceptionHandler { coroutineContext, throwable ->
        println("Something happend: $throwable")
    }

}