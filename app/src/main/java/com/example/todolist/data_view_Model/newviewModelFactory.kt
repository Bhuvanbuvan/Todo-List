package com.example.todolist.data_view_Model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.todolist.database.Repository
import java.lang.IllegalArgumentException

class newviewModelFactory(private val repository: Repository):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TodoViewModel::class.java)){
            return TodoViewModel(repository) as T
        }
        throw IllegalArgumentException("unknown viewmodel factory")
    }
}