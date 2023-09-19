interface Functions<I> {
    fun add(item: I): Int

    fun delete(id: Int): Int

    // редактирование заметки
    fun edit(note_id: Int, title: String, text: String): Int

    // возвращение списка заметок
    fun get(vararg note_ids: Int): ArrayList<I>

    // возвращение заметки по Id
    fun getById(note_id: Int): I

    // создание комментария к заметке
    fun createComment(note_id: Int, message: String): Int

    // удаление комментария
    fun deleteСomment(comment_id: Int): Int

    // редактирование коментария
    fun editComment(comment_id: Int, message: String): Int

    // восстановление удаленного комментария
    fun restoreComment(comment_id: Int): Int
}