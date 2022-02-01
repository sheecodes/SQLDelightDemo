package com.sheecodes.sqldelightdemo.data

import com.sheecodes.sqldelightdemo.NotesDatabase
import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToList
import demo.notesdb.NotesEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class NoteDataSourceImpl(
    db: NotesDatabase
) : NoteDataSource {

    private val queries = db.notesEntityQueries

    override suspend fun getNoteById(id: Long): NotesEntity? {
        return withContext(Dispatchers.IO) {
            queries.getNoteById(id).executeAsOneOrNull()
        }
    }

    override fun getAllNotes(): Flow<List<NotesEntity>> {
        return queries.getAllNotes().asFlow().mapToList()
    }

    override suspend fun deleteNoteById(id: Long) {
        return withContext(Dispatchers.IO) {
            queries.deletePersonById(id)
        }
    }

    override suspend fun insertNote(title: String, description: String, id: Long?) {
        return withContext(Dispatchers.IO) {
            queries.insertNote(id, title, description)
        }
    }
}