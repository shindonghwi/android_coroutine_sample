package wolf.shin.studycoroutine

import android.os.Bundle
import android.util.Log
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

                    launch {
                        launch(Dispatchers.IO + CoroutineName("launch1")) {

                            coroutineContext[CoroutineName]
                            coroutineContext[CoroutineDispatcher]

                        }
                    }

                }

            }
        }
    }
}