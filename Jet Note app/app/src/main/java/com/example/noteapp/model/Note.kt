package com.example.noteapp.model

import android.icu.text.CaseMap.Title
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.Instant
import java.time.LocalDateTime
import java.util.Date
import java.util.UUID

@Entity(tableName = "Notes_Table")
data class Note(
    @PrimaryKey
    val id : UUID = UUID.randomUUID(),
    @ColumnInfo(name = "Note_title")
    val title: String,
    @ColumnInfo(name = "Note_description")
    val description : String,
    @ColumnInfo(name = "Note_entry_date")
    val entryDate : Date = Date.from(Instant.now())
) {
}