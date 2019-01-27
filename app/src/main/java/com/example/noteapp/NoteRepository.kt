package com.example.noteapp

import android.app.Application
import android.arch.lifecycle.LiveData
import android.os.AsyncTask

class NoteRepository(application: Application) {

    private var noteDao: NoteDao

    private var allNotes: LiveData<List<Note>>

    init {
        val database: NoteDatabase = NoteDatabase.getInstance(
            application.applicationContext
        )!!
        noteDao = database.noteDao()
        allNotes = noteDao.getAllNotes()
    }

    fun insert(note:Note) {
        val insertNoteAsyncTask = InsertNoteAsyncTask(noteDao).execute(note)
    }

    fun deleteAllNotes() {
        val deleteAllNotesAsyncTask = DeleteAllNotesAsyncTask(noteDao).execute()
    }

    fun getAllNotes() : LiveData<List<Note>> {
        return allNotes
    }

    private class InsertNoteAsyncTask(val noteDao: NoteDao): AsyncTask<Note, Unit, Unit>() {

        override fun doInBackground(vararg params: Note?) {
            noteDao.insert(params[0]!!)
        }
    }

    private class DeleteAllNotesAsyncTask(val noteDao: NoteDao): AsyncTask<Unit, Unit, Unit>() {
        override fun doInBackground(vararg params: Unit?) {
            noteDao.deleteAllNotes()
        }

    }

}