package wolf.shin.studycoroutine

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.coroutines.*
import wolf.shin.studycoroutine.ui.theme.StudyCoroutineTheme
import kotlin.random.Random
import kotlin.system.measureTimeMillis

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StudyCoroutineTheme {
                runBlocking {
                    val elapsedTime = measureTimeMillis {
                        val value1 = getRandom1()
                        val value2 = getRandom2()

                        Log.d(TAG, "$value1 + $value2 = ${value1 + value2}")
                    }
                    Log.d(TAG, "$elapsedTime")
                }
            }
        }
    }

    companion object{
        val TAG = "CoroutineStudy"
    }

}

suspend fun getRandom1(): Int{
    delay(1000L)
    return Random.nextInt(0, 500)
}

suspend fun getRandom2(): Int{
    delay(1000L)
    return Random.nextInt(0, 500)
}

