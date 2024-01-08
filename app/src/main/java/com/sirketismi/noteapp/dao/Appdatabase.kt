package com.sirketismi.noteapp.dao

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sirketismi.noteapp.model.NoteEntity
import com.sirketismi.noteapp.model.User

@Database(entities = [NoteEntity::class], version = 1)
abstract class Appdatabase : RoomDatabase() {
    abstract fun noteDao() : NotesDao
}