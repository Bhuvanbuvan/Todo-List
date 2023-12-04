package com.example.todolist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.R
import com.example.todolist.databinding.CardviewBinding
import com.example.todolist.model.TodoModel

class myadapter(private val datas:List<TodoModel>,val clickListener:(TodoModel)->Unit):RecyclerView.Adapter<myadapter.myViewHolder>() {

    class myViewHolder(val binding:CardviewBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(todo:TodoModel,clickListener: (TodoModel) -> Unit){
            binding.tvtitle.text=todo.title
            binding.tvcontent.text=todo.content

            binding.linearLayout.setOnClickListener(){
                clickListener(todo)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myViewHolder {
        var layoutInflater=LayoutInflater.from(parent.context)
        var binding:CardviewBinding=DataBindingUtil.inflate(layoutInflater, R.layout.cardview,parent,false)
        return myViewHolder(binding)
    }

    override fun onBindViewHolder(holder: myViewHolder, position: Int) {
        holder.bind(datas[position],clickListener)
    }

    override fun getItemCount(): Int {
        return datas.size
    }
}