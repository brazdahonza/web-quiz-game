package engine.dto

class CreatedQuestionResponse(
    val id: Int,
    val title: String,
    val text: String,
    val options: MutableList<String>
)
