package com.example.todolist.database

import com.example.todolist.model.TodoModel

class Repository(private val dao: DAO) {
    val todos=dao.todos()

    suspend fun insert(data:TodoModel):Long{
        return dao.insert(data)
    }

    suspend fun delete(data: TodoModel){
        return dao.delete(data)
    }

    suspend fun update(data: TodoModel){
        return dao.update(data)
    }
}