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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StudyCoroutineTheme {

                runBlocking {
                    async(Dispatchers.Unconfined){
                        Log.d(TAG, "${Thread.currentThread().name}")
                        delay(100L)
                        Log.d(TAG, "${Thread.currentThread().name}")
                        delay(100L)
                        Log.d(TAG, "${Thread.currentThread().name}")
                    }
                }

            }
        }
    }
}