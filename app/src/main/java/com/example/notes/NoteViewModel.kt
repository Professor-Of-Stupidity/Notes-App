package com.example.notes

import androidx.lifecycle.AndroidViewModel
import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteViewModel(application: Application): AndroidViewModel(application) {

    val allNotes : LiveData<List<Note>>
    private val repository: NoteRepository
    init {
        val dao = NoteDatabase.getDatabase(application).getNoteDao()
        repository =  NoteRepository(dao)
        allNotes = repository.allNotes

    }

    //Since it is a suspend function that's why we need viewModelScope
    //because it provides a coroutine
    fun deleteNote(note: Note) = viewModelScope.launch(Dispatchers.IO)
    {
        repository.delete(note)
    }

    fun insertNote(note:Note) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(note)
    }
}