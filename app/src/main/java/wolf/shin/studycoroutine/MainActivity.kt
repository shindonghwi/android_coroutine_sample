package wolf.shin.studycoroutine

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.actor
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import wolf.shin.studycoroutine.channel.channel1
import wolf.shin.studycoroutine.channel.channel2
import wolf.shin.studycoroutine.flow.*
import wolf.shin.studycoroutine.ui.theme.StudyCoroutineTheme
import java.util.concurrent.atomic.AtomicInteger
import kotlin.random.Random
import kotlin.system.measureTimeMillis

val TAG = "CoroutineStudy"

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StudyCoroutineTheme {


                /** Flow 2-2 연산자 */
//                main_flow_2_2()
//                flowFilter()
//                flowFilterNot()
//                flowMapAndFilterNot()
//                flowTransform()
//                flowTake()
//                flowTakeWhile()
//                flowDrop()
//                flowDropWhile()
//                flowReduce()
//                flowFold()
//                flowCount()

                /** Flow 2-3 플로우 컨텍스트 */
//                main_flow_2_3()
//                flowSimpleWithContext()
//                flowSimpleFlowOn()

                /** Flow buffer */
//                flowBuffer()
//                flowConflate()
//                flowCollectLatest()

                /** Flow 결합 */
//                flowZip()
//                flowCombine()

                /** Flow 플래트닝 */
//                flowFlatMapConcat()
//                flowFlatMapMerge()
//                flowFlatMapLatest()

                /** Flow 예외처리 */
//                flowTryCatch()
//                flowTryCatch1()
//                flowTryCatch2()
//                flowTryCatch3()

                /** Flow 완료처리 */
//                flowFinally1()
//                flowFinally2()

                /** Flow 런칭 */
//                flowEvent1()
//                flowEvent2()

                /** Channel 기초 */
//                channel1()
//                channel2()

            }
        }
    }
}