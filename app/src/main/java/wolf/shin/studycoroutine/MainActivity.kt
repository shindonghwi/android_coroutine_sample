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

                val scope = CoroutineScope(Dispatchers.Default)

                val job = scope.launch(Dispatchers.IO) {
                    launch { printRandom() }
                }

                Thread.sleep(1000L)


            }
        }
    }

    suspend fun printRandom() {
        delay(500L)
        println(Random.nextInt(0, 500))
    }
}