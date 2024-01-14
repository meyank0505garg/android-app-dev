package com.example.noteapp.di

import android.content.Context
import androidx.room.Room
import com.example.noteapp.data.NoteDataBase
import com.example.noteapp.data.NoteDataBaseDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AddModule {
    @Singleton
    @Provides
    fun provideNotesDao(noteDataBase: NoteDataBase) : NoteDataBaseDao
    = noteDataBase.noteDao()

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context) : NoteDataBase
    = Room.databaseBuilder(
        context,
        NoteDataBase::class.java,
        "notes_db"
    ).fallbackToDestructiveMigration()
        .build()

}