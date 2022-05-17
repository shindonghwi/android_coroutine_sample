package wolf.shin.studycoroutine

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import wolf.shin.studycoroutine.ui.theme.StudyCoroutineTheme
import kotlin.random.Random

val TAG = "CoroutineStudy"

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StudyCoroutineTheme {
                main()
            }
        }
    }

    // 플로우 빌더를 통해 값을 방출하고, 수집기를 통해 값을 출력한다
    fun flowSomething(): Flow<Int> = flow {
        repeat(10) {
            emit(Random.nextInt(0, 500))
            kotlinx.coroutines.delay(1000L)
        }
    }

    fun main() = runBlocking {
        flowOf(1, 2, 3, 4, 5).collect { value -> print(value) }
        flow {
            emit(1)
            emit(2)
            emit(3)
            emit(4)
            emit(5)
        }.collect { print(it) }
    }
    // 콜드 스트림 : 요청이 있는 경우에 대해서 1:1 로 값을 전달하기 시작
    // 핫 스트림: 0개 이상의 상대를 향해 지속적으로 값을 전달
}