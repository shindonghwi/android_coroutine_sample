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

                    CoroutineScope(Dispatchers.IO).launch {
                        supervisoredFunc()
                    }.join()

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

    suspend fun supervisoredFunc() = supervisorScope {
        launch { printRandom1() }
        launch(ceh) { printRandom2() }
    }

    val ceh = CoroutineExceptionHandler { coroutineContext, throwable ->
        println("Something happend: $throwable")
    }

}