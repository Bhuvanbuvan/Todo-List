package com.example.todolist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Database
import com.example.todolist.adapter.myadapter
import com.example.todolist.data_view_Model.TodoViewModel
import com.example.todolist.data_view_Model.newviewModelFactory
import com.example.todolist.database.Repository
import com.example.todolist.database.todoDatabase
import com.example.todolist.databinding.ActivityMainBinding
import com.example.todolist.model.TodoModel

class MainActivity : AppCompatActivity() {
    lateinit var binding:ActivityMainBinding
    lateinit var viewmodel:TodoViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=DataBindingUtil.setContentView(this,R.layout.activity_main)

        val ado=todoDatabase.getInstence(application).todos
        val repository=Repository(ado)
        val factory=newviewModelFactory(repository)

        viewmodel=ViewModelProvider(this,factory).get(TodoViewModel::class.java)

        binding.myviewmodel=viewmodel
        binding.lifecycleOwner=this

        displaytodo()
    }

    private fun displaytodo() {
        binding.recycler.layoutManager=LinearLayoutManager(this)
        viewmodel.datas.observe(
            this,
            Observer {
                binding.recycler.adapter=myadapter(it,{selector:TodoModel->process(selector)})
            }
        )
    }

    private fun process(selector: TodoModel) {
        Toast.makeText(this,"Title : ${selector.title}",Toast.LENGTH_LONG).show()
        viewmodel.initbutton(selector)
    }
}