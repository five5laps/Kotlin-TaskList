package com.example.basickotlinapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.basickotlinapp.databinding.ActivityMainBinding
import com.example.basickotlinapp.databinding.ItemTodoBinding

class MainActivity : AppCompatActivity() {

    //lateinit is promise to kotlin that we will init it later
    private lateinit var taskAdapter: TaskAdapter
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        //we want our list to be empty and add tasks dynamically
        taskAdapter = TaskAdapter(mutableListOf())

        binding.rvTodoItems.adapter = taskAdapter
        binding.rvTodoItems.layoutManager = LinearLayoutManager(this)

        binding.btnAddTask.setOnClickListener {
            val taskTitle = binding.etTaskTitle.text.toString()
            if(taskTitle.isNotEmpty()){
                val task = Task(taskTitle)
                Log.d("Debug", task.toString())
                taskAdapter.addTask(task)
                binding.etTaskTitle.text.clear()
            }
        }
        binding.btnDeleteTask.setOnClickListener {
            //logic in deleteTask() (deleting last task)
            taskAdapter.deleteTask()
        }
    }

}

/* TODO:
    Баг с тем, что при добавлении таски название всегда Example
    Баг с удалением
 */