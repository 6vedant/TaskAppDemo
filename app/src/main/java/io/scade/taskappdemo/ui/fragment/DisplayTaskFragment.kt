package io.scade.taskappdemo.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import io.scade.taskappdemo.adapter.TaskItemClickListener
import io.scade.taskappdemo.adapter.TaskRecyclerAdapter
import io.scade.taskappdemo.databinding.FragmentCreateTaskBinding
import io.scade.taskappdemo.databinding.FragmentDisplayTaskBinding
import io.scade.taskappdemo.model.SubTask
import io.scade.taskappdemo.model.Task
import io.scade.taskappdemo.viewmodel.TaskViewModel
import io.scade.taskappdemo.viewmodel.TaskViewModelFactory

class DisplayTaskFragment : Fragment() {
    private lateinit var binding: FragmentDisplayTaskBinding

    private val taskViewModel: TaskViewModel by viewModels<TaskViewModel> {
        TaskViewModelFactory()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDisplayTaskBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.fabAddTask.setOnClickListener {
            val subTask1 = SubTask("kkadfaa", "k1", "erhh", true)
            val subTask2 = SubTask("kkdaaa", "k1", "eaarhh", false)

            val task = Task(
                "k1",
                "Task 2 new title",
                "Here is the description",
                false,
                mutableListOf(subTask1, subTask2),
                listOf("Official", "Immediate"),
                "3-10-2024"
            )

            taskViewModel.addTask(task)
            this.findNavController().navigate(
                DisplayTaskFragmentDirections.actionNavigateToCreateTaskFragment()
            )

        }
        val subTask1 = SubTask("kka", "k", "erhh", true)
        val subTask2 = SubTask("kkaa", "k", "eaarhh", false)

        val task = Task(
            "k",
            "Task New Title",
            "Here is the description",
            true,
            mutableListOf(subTask1, subTask2),
            listOf("Personal", "Immediate"),
            "3-10-2024"
        )

        taskViewModel.addTask(task)


        // init the adapter and bind it to the recyclerview
        val taskRecyclerAdapter =
            taskViewModel.tasksList.value?.let {
                TaskRecyclerAdapter(it, TaskItemClickListener(itemClickListener = {
                    Toast.makeText(context, "clicked on item", Toast.LENGTH_SHORT).show()
                }, subTaskTextClickListener = {
                    it.isSubTaskExpandable = !it.isSubTaskExpandable
                }))
            }

        binding.recyclerViewTasks.layoutManager = LinearLayoutManager(context)
        binding.recyclerViewTasks.setHasFixedSize(true)
        binding.recyclerViewTasks.adapter = taskRecyclerAdapter

        taskViewModel.tasksList.observe(viewLifecycleOwner, Observer {
            Toast.makeText(context, "Updated: " + it.toString(), Toast.LENGTH_SHORT).show()

        })
    }
}