package com.sirketismi.noteapp.di

import android.content.Context
import androidx.room.Room
import com.sirketismi.noteapp.dao.Appdatabase
import com.sirketismi.noteapp.dao.NotesDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideNoteDao(appDatabase : Appdatabase) : NotesDao {
        return appDatabase.noteDao()
    }

    @Singleton
    @Provides
    fun provideAppdatabase(@ApplicationContext appContext: Context) : Appdatabase {
        val instance = Room.databaseBuilder(
            appContext.applicationContext,
            Appdatabase::class.java,
            "notedb"
        ).build()
        return instance
    }
}