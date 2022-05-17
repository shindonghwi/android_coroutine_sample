package wolf.shin.studycoroutine

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import kotlinx.coroutines.flow.*
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

    fun main() = runBlocking {
        // asFlow는 컬렉션이나 시퀀스를 전달해서 플로우를 만들 수 있음
        listOf(1,2,3).asFlow().collect { value -> println(value) }
        (6..10).asFlow().collect{
            println(it)
        }
    }
    // 콜드 스트림 : 요청이 있는 경우에 대해서 1:1 로 값을 전달하기 시작
    // 핫 스트림: 0개 이상의 상대를 향해 지속적으로 값을 전달
}