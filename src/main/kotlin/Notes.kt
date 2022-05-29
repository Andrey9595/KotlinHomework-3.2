import java.util.*

class Notes {
    private var arreyNotes = mutableListOf<Note>()
    private var noteId = 0
    private var commentId = 0

    /*Создает новую заметку у текущего пользователя.*/
    fun add(title: String, text: String): Int {
        noteId += 1
        val note = Note(title = title, text = text, noteId = noteId)
        arreyNotes.add(note)
        return note.noteId
    }

    /*Добавляет новый комментарий к заметке. */
    fun createComment(noteId: Int, message: String): Int {

//        val comments: Comment = Comment(
//            commentId = Random().nextInt(), message = masseg
//        )
//        val note = arreyNotes.find { it.noteid == noteid }
//        return comments.noteid


        for (note in arreyNotes) {
            if (note.noteId == noteId) {
                commentId += 1
                val comment = Comment(commentId = commentId, noteId = noteId, message = message)
                note.comments.add(comment)
                return comment.noteId
            }
        }
        return 0
    }

    /*    Удаляет заметку текущего пользователя.*/
    fun delete(noteId: Int): Boolean = arreyNotes.removeIf { it.noteId == noteId }

    /*    Удаляет комментарий к заметке.*/
    fun deleteComment(commentId: Int): Boolean {
//    = arreyNotes.removeIf { it.comments == commentId }
        for (note in arreyNotes) {
            for (comment in note.comments) {
                if (comment.commentId == commentId) {
                    val delComment = comment.copy(isDelete = true)
                    note.comments.remove(comment)
                    note.comments.add(delComment)
                    return true
                }
            }
        }
        return false
    }

    /*  Редактирует заметку текущего пользователя.*/
    fun edit(noteId: Int, title: String, text: String): Boolean {
        for (note in arreyNotes) {
            if (note.noteId == noteId) {
                val editNote = note.copy(title = title, text = text)
                arreyNotes.remove(note)
                arreyNotes.add(editNote)
                return true
            }
        }
        return false
    }

    /*    Редактирует указанный комментарий у заметки.*/
    fun editComment(commentId: Int, message: String): Boolean {
        for (note in arreyNotes) {
            for (comment in note.comments) {
                if (comment.commentId == commentId) {
                    val editComment = comment.copy(message = message)
                    note.comments.remove(comment)
                    note.comments.add(comment)
                    return true
                }
            }
        }
        return false
    }

    /*    Возвращает список заметок, созданных пользователем.*/
    fun get(noteIds: Set<Int>, sort: Int = 0): MutableList<Note> {
        val getNotes = mutableListOf<Note>()
        for (noteId in noteIds) {
            for (note in arreyNotes) {
                if (note.noteId == noteId) {
                    getNotes.add(note)
                }
            }
        }
        getNotes.sortBy { it.date }
        getNotes.reverse()
        if (sort == 1) getNotes.reverse()

        return getNotes
    }

    /*    Возвращает заметку по её id.*/
    fun getById(noteId: Int): Note {
        for (note in arreyNotes) {
            if (note.noteId == noteId) {
                return note
            }
        }
        throw NoteNotFoundException("no note with id $noteId")
    }

    /*    Возвращает список комментариев к заметке. sort
    Сортировка результатов (0 — по дате добавления в порядке возрастания,
     1 — по дате добавления в порядке убывания)*/
    fun getComment(noteId: Int, sort: Int = 0): MutableList<Comment> {
        for (note in arreyNotes) {
            if (note.noteId == noteId) {
                val getComments = note.comments
                getComments.sortBy { it.date }
                getComments.reverse()
                if (sort == 1) getComments.reverse()
                return getComments
            }
        }
        throw NoteNotFoundException("no note with id $noteId")
    }

    /*    Восстанавливает удалённый комментарий.*/
    fun restoreComment(commentId: Int): Boolean {
        for (note in arreyNotes) {
            for (comment in note.comments) {
                if (comment.commentId == commentId) {
                    val restoreComment = comment.copy(isDelete = false)
                    note.comments.remove(comment)
                    note.comments.add(restoreComment)
                    return true
                }
            }
        }
        return false
    }
}
