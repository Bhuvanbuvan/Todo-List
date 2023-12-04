package com.example.todolist.data_view_Model

import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todolist.database.Repository
import com.example.todolist.model.TodoModel
import kotlinx.coroutines.launch

class TodoViewModel(private val repository: Repository):ViewModel(),Observable {

    var datas=repository.todos
    var isupdateordelete=false
    private lateinit var holder:TodoModel
    @Bindable
    val inputTitle=MutableLiveData<String?>()
    @Bindable
    val inputContent=MutableLiveData<String?>()
    @Bindable
    val saveOrUpdatetext=MutableLiveData<String?>()
    @Bindable
    val cancelOrDeletetext=MutableLiveData<String?>()
    init {
        saveOrUpdatetext.value="Save"
        cancelOrDeletetext.value="Cancel"
    }

    fun push(){
        if (isupdateordelete){
           holder.title=inputTitle.value!!
            holder.content=inputContent.value!!
            updatedata(holder)
        }
        else{
            val title=inputTitle.value!!
            val content=inputContent.value!!
            insert(TodoModel(0,title, content))
        }
    }

    private fun updatedata(holder: TodoModel)=viewModelScope.launch {
        repository.update(holder)
        isupdateordelete=false
        saveOrUpdatetext.value="Save"
        cancelOrDeletetext.value="Cancel"
        inputTitle.value=null
        inputContent.value=null

    }

    private fun insert(todoModel: TodoModel)=viewModelScope.launch {
        repository.insert(todoModel)
        inputContent.value=null
        inputTitle.value=null

    }

    fun cancel(){
        if (isupdateordelete){
            viewModelScope.launch {
                repository.delete(holder)
                inputTitle.value=null
                inputContent.value=null
                saveOrUpdatetext.value="Save"
                cancelOrDeletetext.value="Cancel"
                isupdateordelete=false
            }
        }else {
            inputTitle.value = null
            inputContent.value = null
        }
    }

    fun initbutton(selector: TodoModel) {
        inputTitle.value=selector.title
        inputContent.value=selector.content
        saveOrUpdatetext.value="Update"
        cancelOrDeletetext.value="Delete"
        isupdateordelete=true
        holder=selector
    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }


}