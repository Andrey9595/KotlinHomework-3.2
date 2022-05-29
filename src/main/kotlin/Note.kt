data class Note(
    val noteId: Int, //идентификатор заметки.
    val comments: MutableList<Comment> = mutableListOf(), //количество комментариев
    val date: Int = 0,  //дата создания
    val title: String, //заголовок заметки.
    val text: String, //текст заметки.
    val isDelete: Boolean = false, //удаление заметки
)