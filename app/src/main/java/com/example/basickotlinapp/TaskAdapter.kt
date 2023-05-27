package com.example.basickotlinapp

import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.basickotlinapp.databinding.ActivityMainBinding
import com.example.basickotlinapp.databinding.ItemTodoBinding

/*
ViewHolder класс используется для представления отдельного элемента списка внутри RecyclerView.
*/

class TaskAdapter(
    private val tasks: MutableList<Task>
    ) : RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {

    //this View is androidx.constraintlayout.widget.ConstraintLayout, only shows items that visible on screen
    class TaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        return TaskViewHolder(
            //takes item_todo.xml and convert it to view that we can work in kotlin
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_todo,
                parent,
                false
            )
        )
    }
    //add task to list
    fun addTask(task: Task){
        tasks.add(task)
        notifyItemInserted(tasks.size-1)//add to last item of list
    }

    //delete checked task
    fun deleteTask(){
        tasks.removeAll { task ->
            task.isChecked
        }
        notifyDataSetChanged() //update all list
    }

    //check or uncheck checkbox of current task
    private fun toggleStrikeThrough(tvTaskTitle: TextView, isChecked: Boolean){
        if(isChecked) {
            tvTaskTitle.paintFlags = tvTaskTitle.paintFlags or STRIKE_THRU_TEXT_FLAG
        } else {
            tvTaskTitle.paintFlags = tvTaskTitle.paintFlags and STRIKE_THRU_TEXT_FLAG.inv() //inverted
        }
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val curTask = tasks[position] //current item
        val binding = ItemTodoBinding.bind(holder.itemView)

        holder.itemView.apply {
            binding.tvTaskTitle.text = curTask.title
            binding.cbDone.isChecked = curTask.isChecked
            toggleStrikeThrough(binding.tvTaskTitle, curTask.isChecked)
            binding.cbDone.setOnCheckedChangeListener { _, isChecked ->
                toggleStrikeThrough(binding.tvTaskTitle, isChecked)
                curTask.isChecked = isChecked
            } //triggers when checkbox is changed
        }
    }

    override fun getItemCount(): Int {
        //amount of items
        return tasks.size
    }
}