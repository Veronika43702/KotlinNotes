import org.junit.Test

import org.junit.Assert.*

class NoteServiceTest {

    @Test
    fun addSuccsess() {
        val service = NoteService()
        assertEquals(1,service.add("Заметка 1", "проверка add"))
    }

    @Test
    fun createCommentSuccess() {
        val service = NoteService()
        service.add("Заметка 1", "проверка add")
        assertEquals(1,service.createComment(1,"проверка createComment"))
    }

    @Test(expected = NoObjectFoundById::class)
    fun createCommentFailDueNoNoteWithId() {
        val service = NoteService()
        assertEquals(1,service.createComment(1,"проверка createComment"))
    }

    @Test
    fun deleteSuccess() {
        val service = NoteService()
        service.add("Заметка 1", "проверка add")
        assertEquals(1, service.delete(1))

    }

    @Test(expected = NoObjectFoundById::class)
    fun deleteFails() {
        val service = NoteService()
        service.delete(1)

    }

    @Test
    fun deleteСommentSuccsess() {
        val service = NoteService()
        service.add("Заметка 1", "проверка add")
        service.createComment(1,"проверка createComment")
        assertEquals(1,service.deleteСomment(1))
    }

    @Test(expected = NoObjectFoundById::class)
    fun deleteСommentFail() {
        val service = NoteService()
       service.deleteСomment(1)
    }

    @Test
    fun editSucsess() {
        val service = NoteService()
        service.add("Заметка 1", "проверка add")
        assertEquals(1, service.edit(1,"Измененная заметка", "проверка edit"))
    }

    @Test(expected = NoObjectFoundById::class)
    fun editFail() {
        val service = NoteService()
       service.edit(1,"Измененная заметка", "проверка edit")
    }

    @Test
    fun editCommentSuccess() {
        val service = NoteService()
        service.add("Заметка 1", "проверка add")
        service.createComment(1,"проверка createComment")
        assertEquals(1,service.editComment(1, "текст"))
    }

    @Test(expected = NoObjectFoundById::class)
    fun editCommentFailDueDeletedComment() {
        val service = NoteService()
        service.add("Заметка 1", "проверка add")
        service.createComment(1,"проверка createComment")
        service.deleteСomment(1)
        service.editComment(1, "текст")
    }

    @Test(expected = NoObjectFoundById::class)
    fun editCommentFailDueNoCommentFound() {
        val service = NoteService()
       service.editComment(1, "текст")
    }



    @Test
    fun getSuccess() {
        val service = NoteService()
        service.add("Заметка 1", "проверка add")
        service.add("Заметка 2", "проверка add")
        service.add("Заметка 3", "проверка add")

        val result = arrayListOf<Notes>(service.getAllNotes()[0], service.getAllNotes()[2])
        assertEquals(result ,service.get(1,3))
    }

    @Test
    fun getByIdSuccess() {
        val service = NoteService()
        service.add("Заметка 1", "проверка add")
        service.add("Заметка 2", "проверка add")

        val result = (service.getAllNotes()[0])

        assertEquals(result, service.getById(1))
    }

    @Test(expected = NoObjectFoundById::class)
    fun getByIdFail() {
        val service = NoteService()
        service.getById(1)
    }

    @Test
    fun getCommentsSuccess() {
        val service = NoteService()
        service.add("Заметка 1", "проверка add")
        service.add("Заметка 2", "проверка add")
        service.createComment(1,"проверка createComment")
        service.createComment(2,"проверка createComment")

        assertEquals(service.getAllComments(),service.getComments(1,2))
    }

    @Test
    fun restoreComment() {
        val service = NoteService()
        service.add("Заметка 1", "проверка add")
        service.add("Заметка 2", "проверка add")
        service.createComment(1,"проверка createComment")
        service.createComment(2,"проверка createComment")
        service.deleteСomment(2)

        assertEquals(1,service.restoreComment(2))

    }

    @Test(expected = NoObjectFoundById::class)
    fun restoreCommentFailDueNotDeletedComment() {
        val service = NoteService()
        service.add("Заметка 1", "проверка add")
        service.add("Заметка 2", "проверка add")
        service.createComment(1,"проверка createComment")
        service.createComment(2,"проверка createComment")

        service.restoreComment(2)

    }

    @Test(expected = NoObjectFoundById::class)
    fun restoreCommentFailDueNoCommentWithId() {
        val service = NoteService()
        service.restoreComment(2)

    }
}