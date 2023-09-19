class NoteService: Functions<Notes> {
    private var idNote: Int = 1
    private var idComment: Int = 1
    private var notes = ArrayList<Notes>()
    private var comments = ArrayList<Comments>()

    override fun add(note: Notes): Int {
        val noteNew = note.copy(id = idNote)
        idNote += 1
        notes += noteNew
        return notes.last().id
    }

    override fun delete(note_id: Int): Int {
        for ((index, note) in notes.withIndex()) {
            if (note.id == note_id) {
                notes.remove(notes[index])
                for (comment in comments){
                    if (comment.note_id == note_id){
                        comments.remove(comment)
                    }
                }
                return 1
            }
        }
        return throw NoObjectFoundById("Удаление заметки невозможно, т.к. не существует заметки с id $note_id")
    }


    // создание комментария к заметке
    override fun createComment(note_id: Int, message: String): Int {
        notes.find { it.id == note_id }
            ?: throw NoObjectFoundById("Создание комментария невозможно, т.к. не существует заметки с id $note_id")

        val comment = Comments(idComment, note_id, message)
        idComment += 1
        comments += comment
        return comment.id
    }


    // удаление комментария
    override fun deleteСomment(comment_id: Int): Int {
        for ((index, comment) in comments.withIndex()) {
            if (comment.id == comment_id) {
                comments.set(index, comments[index].copy(isExist = false))
                return 1
            }
        }
        return throw NoObjectFoundById("Удаление коментария невозможно, т.к. не существует заметки с id $comment_id")
    }

    // редактирование заметки
    override fun edit(note_id: Int, title: String, text: String): Int {
        for ((index, note) in notes.withIndex()) {
            if (note.id == note_id) {
                notes.set(index, notes[index].copy(title = title, text = text))
                return 1
            }
        }
        return throw NoObjectFoundById("Удаление заметки невозможно, т.к. не существует заметки с id $note_id")

    }

    // редактирование коментария
    override fun editComment(comment_id: Int, message: String): Int {
        for ((index, comment) in comments.withIndex()) {
            if (comment.id == comment_id && comment.isExist) {
                comments.set(index, comments[index].copy(message = message))
                return 1
            } else if (comment.id == comment_id && !comment.isExist) {
                return throw NoObjectFoundById("Удаление комментария невозможно, т.к. комментарий с id $comment_id удален")
            }
        }
        return throw NoObjectFoundById("Удаление комментария невозможно, т.к. не существует комментария с id $comment_id")

    }

    // возвращение списка заметок
    override fun get(vararg note_ids: Int): ArrayList<Notes> {
        val notesToGet = ArrayList<Notes>()
        note_ids.forEach {
            for (note in notes) {
                if (note.id == it) {
                    notesToGet.add(note)
                }
            }
        }
        return notesToGet
    }

    // возвращение заметки по Id
    override fun getById(note_id: Int): Notes {
        return notes.find { it.id == note_id }
            ?: throw NoObjectFoundById("Не существует заметки с id $note_id")
    }

    // возвращение списка комментариев
    fun getComments(vararg note_ids: Int): ArrayList<Comments> {
        val commentsToGet = ArrayList<Comments>()
        note_ids.forEach {
            for (comment in comments) {
                if (comment.note_id == it && comment.isExist) {
                    commentsToGet.add(comment)
                }
            }
        }
        return commentsToGet
    }

    // восстановление удаленного комментария
    override fun restoreComment(comment_id: Int): Int {
        for ((index, comment) in comments.withIndex()) {
            if (comment.id == comment_id && !comment.isExist) {
                comments.set(index, comments[index].copy(isExist = true))
                return 1
            } else if (comment.id == comment_id && comment.isExist) {
                return throw NoObjectFoundById("Восстановление комментария невозможно, т.к. комментарий не удален")
            }
        }
        return throw NoObjectFoundById("Восстановление комментария невозможно, т.к. не существует комментария с id $comment_id")

    }

    fun getAllNotes(): ArrayList<Notes>{
        return notes
    }

    fun getAllComments(): ArrayList<Comments>{
        return comments
    }


}


fun main(args: Array<String>) {
    val service = NoteService()
//    println(service.add(Notes(title = "Заметка 1", text = "проверка add")))
//    println(service.add(Notes(title = "Заметка 2", text = "проверка add")))
//    println(service.add(Notes(title = "Заметка 3", text = "проверка add")))
//
//    println(service.getAllNotes())
//    service.createComment(2, "Коммент 1 к Заметке 2")
//    service.createComment(1, "Коммент 1 к Заметке 1")
//    println(service.delete(2))
//    println(service.getAllNotes())
//    service.edit(3, "Измененнная заметка", "проверка edit")
//
//    service.createComment(1, "Коммент 1 к Заметке 2")
//    service.createComment(1, "Коммент 1 к Заметке 1")
//    service.createComment(3, "Коммент 2 к Заметке 2")
//    service.createComment(3, "Коммент 2 к Заметке 2")
//    println(service.getComments(1, 2, 3))
//    service.deleteСomment(2)
//    service.deleteСomment(3)
//    println("До " + service.getComments(1, 2, 3))
//    service.restoreComment(2)
//    println("После " + service.getComments(1,2,3))

}