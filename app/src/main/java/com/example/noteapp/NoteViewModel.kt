package com.example.noteapp

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData

class NoteViewModel(application: Application) : AndroidViewModel(application) {

    private var noteRepository: NoteRepository = NoteRepository(application)
    private var allNotes: LiveData<List<Note>> = noteRepository.getAllNotes()

    fun insert(note: Note) {
        noteRepository.insert(note)
    }

    fun deleteAllNotes() {
        noteRepository.deleteAllNotes()
    }

    fun getAllNotes() : LiveData<List<Note>> {
        return noteRepository.getAllNotes()
    }
}