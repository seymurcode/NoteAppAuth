package com.sirketismi.noteapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey(autoGenerate = true) val id : Int = 0,
    @ColumnInfo(name = "name") val name : String?,
    @ColumnInfo(name = "surname") val surname : String?,
    @ColumnInfo(name = "age") val age : Int) {
}