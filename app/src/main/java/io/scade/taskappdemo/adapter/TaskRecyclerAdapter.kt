package io.scade.taskappdemo.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.GONE
import androidx.recyclerview.widget.RecyclerView.VISIBLE
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import io.scade.taskappdemo.databinding.TaskRecyclerItemBinding
import io.scade.taskappdemo.model.Task
import io.scade.taskappdemo.viewmodel.TaskViewModel

public class TaskRecyclerAdapter constructor(
    var tasks: List<Task>,
    val clickListener: TaskItemClickListener,
    private val viewModel: TaskViewModel,
) : RecyclerView.Adapter<TaskRecyclerAdapter.ItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding =
            TaskRecyclerItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding)

    }

    override fun getItemCount(): Int {
        return tasks.size
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(tasks[position], clickListener, viewModel)
    }

    class ItemViewHolder constructor(private val binding: TaskRecyclerItemBinding) :
        ViewHolder(binding.root) {
        fun bind(item: Task?, clickListener: TaskItemClickListener, viewModel: TaskViewModel) {
            item.let {
                binding.task = it
                binding.clickListener = clickListener


                val subTaskAdapter = SubTaskNestedRecyclerAdapter(
                    it!!,
                    SubTaskItemClickListener { subTask, parentTask ->
                        Toast.makeText(
                            binding.root.context,
                            "Clicked on sub-task: ${subTask.title}",
                            Toast.LENGTH_SHORT
                        ).show()
                    }, viewModel)

                binding.subTasksRecyclerView.layoutManager =
                    LinearLayoutManager(binding.root.context)
                binding.subTasksRecyclerView.setHasFixedSize(true)
                binding.subTasksRecyclerView.adapter = subTaskAdapter


                // Add OnCheckedChangeListener for CheckBox
                binding.checkBoxTask.setOnCheckedChangeListener { buttonView, isChecked ->
                    it.isCompleted = isChecked
                    if(isChecked) {
                        for(subtask in it.subTasks!!) {
                            subtask.let {
                                subtask.isCompleted = isChecked
                            }
                        }
                    }
                    viewModel.updateTask(updatedTask = it)
                }
                // Toggle visibility of expandable layout
                // binding.expandableLayout.visibility = if (it!!.isSubTaskExpandable) View.VISIBLE else View.GONE

                binding.executePendingBindings()
                


            }
        }
    }
}

class TaskItemClickListener(
    val itemClickListener: (task: Task) -> Unit,
    val subTaskTextClickListener: (task: Task) -> Unit
) {
    fun onItemClick(task: Task) = itemClickListener(task)
    fun onSubTaskClick(task: Task) = subTaskTextClickListener(task)

}
