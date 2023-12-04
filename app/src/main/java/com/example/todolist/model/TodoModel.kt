package com.example.todolist.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "todo")
data class TodoModel(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "todo_id")
    val id:Int,

    @ColumnInfo(name = "todo_title")
    var title:String,

    @ColumnInfo(name = "todo_content")
    var content:String
)
