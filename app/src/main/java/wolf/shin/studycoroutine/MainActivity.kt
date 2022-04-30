package wolf.shin.studycoroutine

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import wolf.shin.studycoroutine.ui.theme.StudyCoroutineTheme
import kotlin.random.Random

val TAG = "CoroutineStudy"

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StudyCoroutineTheme {
                runBlocking {
                    try {
                        doSomething()
                    } finally {
                        Log.d(TAG, "doSomething failed")
                    }
                }
            }
        }
    }
}

suspend fun getRandom1(): Int {
    try {
        delay(1000L)
        return Random.nextInt(0, 500)
    } finally {
        Log.d(TAG, "getRandom1 cancel")
    }

}

suspend fun getRandom2(): Int {
    delay(1000L)
    throw IllegalStateException()
}

suspend fun doSomething() = coroutineScope {
    val value1 = async { getRandom1() }
    val value2 = async { getRandom2() }

    try {
        Log.d(TAG, "result ${value1.await()} + ${value2.await()} = ${value1.await() + value2.await()}")
    } finally {
        Log.d(TAG, "doSomething cancel")
    }
}