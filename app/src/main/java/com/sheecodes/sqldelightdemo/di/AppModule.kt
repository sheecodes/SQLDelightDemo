package com.sheecodes.sqldelightdemo.di

import android.app.Application
import com.sheecodes.sqldelightdemo.NotesDatabase
import com.sheecodes.sqldelightdemo.data.NoteDataSource
import com.sheecodes.sqldelightdemo.data.NoteDataSourceImpl
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideSqlDriver(app: Application): SqlDriver {
        return AndroidSqliteDriver(
            schema = NotesDatabase.Schema,
            context = app,
            name = "notes.db"
        )
    }

    @Provides
    @Singleton
    fun providesNotesDataSource(driver: SqlDriver): NoteDataSource {
        return NoteDataSourceImpl(
            NotesDatabase(driver)
        )
    }
}