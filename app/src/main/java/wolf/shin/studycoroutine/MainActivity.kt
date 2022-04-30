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

                    // main
                    launch { Log.d(TAG, "1 부모 컨텍스트: ${Thread.currentThread().name}") }

                    // DefaultDispatcher- Core 수에 비례하는 스레프 풀에서 수행됨.
                    launch(Dispatchers.Default) { Log.d(TAG, "2 Default ${Thread.currentThread().name}") }

                    // DefaultDispatcher- Core 수 보다 훨씬 많은 스레드를 가지는 스레드 풀, IO 작업은 CPU를 덜 사용한다.
                    launch(Dispatchers.IO) { Log.d(TAG, "4 IO ${Thread.currentThread().name}") }

                    // main 어디에도 속하지 않음. 어느 스레드에서 수행되는지 정확히 알 수 없다.
                    launch(Dispatchers.Unconfined) { Log.d(TAG, "5 Unconfined ${Thread.currentThread().name}") }

                    // new Thread 새로운 스레드를 생성하여 동작함.
                    launch(newSingleThreadContext("Wolf Developer")) { Log.d(TAG, "6 newSingleThreadContext ${Thread.currentThread().name}") }

                }

            }
        }
    }
}