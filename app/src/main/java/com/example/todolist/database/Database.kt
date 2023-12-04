package com.example.todolist.database

import android.content.Context
import androidx.room.Database
import androidx.room.Entity
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.todolist.model.TodoModel
import kotlin.reflect.typeOf

@Database(entities = [TodoModel::class], version = 1)
abstract class todoDatabase :RoomDatabase(){
    abstract val todos:DAO

    //singleton
    companion object{
        @Volatile
        private var INSTENCE:todoDatabase?=null
        fun getInstence(context: Context): todoDatabase {
            synchronized(this){
                var instence= INSTENCE
                if (instence==null){
                    instence= Room.databaseBuilder(context.applicationContext,todoDatabase::class.java,"todo_db").build()
                }
                return instence
            }
        }
    }
}