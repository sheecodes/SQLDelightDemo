package com.sheecodes.sqldelightdemo.data

import demo.notesdb.NotesEntity
import kotlinx.coroutines.flow.Flow

interface NoteDataSource {

    suspend fun getNoteById(id: Long): NotesEntity?

    fun getAllNotes(): Flow<List<NotesEntity>>

    suspend fun deleteNoteById(id: Long)

    suspend fun insertNote(title: String, description: String,id: Long? = null)
}