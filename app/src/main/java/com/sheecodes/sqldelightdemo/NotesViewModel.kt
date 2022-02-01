package com.sheecodes.sqldelightdemo

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sheecodes.sqldelightdemo.data.NoteDataSource
import dagger.hilt.android.lifecycle.HiltViewModel
import demo.notesdb.NotesEntity
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotesViewModel @Inject constructor(
    private val noteDataSource: NoteDataSource
): ViewModel() {

    val notes = noteDataSource.getAllNotes()
    var noteDetails = MutableLiveData<NotesEntity>()

    fun insertNote(title: String, description: String){
        if (title.isNullOrEmpty() || description.isNullOrEmpty()){
            return
        }

        viewModelScope.launch {
            noteDataSource.insertNote(title, description)
        }
    }

    fun deleteNote(id: Long){
        viewModelScope.launch {
            noteDataSource.deleteNoteById(id)
        }
    }

    fun getNoteById(id: Long){
        viewModelScope.launch {
            noteDetails.value = noteDataSource.getNoteById(id)
        }
    }
}