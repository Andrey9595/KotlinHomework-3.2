data class Comment(
    val commentId: Int, //идентификатор комментария
    val noteId: Int, //идентификатор заметки. положительное число
    val date: Long = System.currentTimeMillis(),  // дата добавления комментария в милисекундах
    val message: String, //текст комментария.
    val isDelete: Boolean = false, // удаление комментария
)
