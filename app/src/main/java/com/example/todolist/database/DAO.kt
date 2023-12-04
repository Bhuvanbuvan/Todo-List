package com.example.todolist.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.todolist.model.TodoModel

@Dao
interface DAO {
    @Insert
    suspend fun insert(data:TodoModel):Long

    @Update
    suspend fun update(data: TodoModel)

    @Delete
    suspend fun delete(data: TodoModel)

    @Query("SELECT * FROM todo")
    fun todos():LiveData<List<TodoModel>>
}