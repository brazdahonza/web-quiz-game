package engine.model

import com.fasterxml.jackson.annotation.JsonIgnore
import java.util.concurrent.atomic.AtomicInteger

class Question(
    val title: String,
    val text: String,
    val options: MutableList<String>,
    val correctAnswer: Int
) {
    val id: Int = createId()

    companion object {
        private val counter = AtomicInteger(0)

        private fun createId(): Int {
            return counter.incrementAndGet()
        }
    }
}
