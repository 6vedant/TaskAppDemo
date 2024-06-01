package io.scade.taskappdemo.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import io.scade.taskappdemo.databinding.SubtaskRecyclerItemBinding
import io.scade.taskappdemo.databinding.TaskRecyclerItemBinding
import io.scade.taskappdemo.model.SubTask
import io.scade.taskappdemo.model.Task

class SubTaskNestedRecyclerAdapter(val task: Task, private val clickListener: SubTaskItemClickListener) :
    RecyclerView.Adapter<SubTaskNestedRecyclerAdapter.SubTaskItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubTaskItemViewHolder {
        val binding =
            SubtaskRecyclerItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SubTaskNestedRecyclerAdapter.SubTaskItemViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return task.subTasks?.size ?: 0
    }

    override fun onBindViewHolder(holder: SubTaskItemViewHolder, position: Int) {
        holder.bind(task.subTasks?.get(position), task, clickListener)
    }

    class SubTaskItemViewHolder(val binding: SubtaskRecyclerItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: SubTask?, task: Task?, clickListener: SubTaskItemClickListener) {
            item.let {
                binding.subTask = it
                binding.task = task
                binding.clickListener = clickListener
                binding.executePendingBindings()
            }
        }
    }
}

class SubTaskItemClickListener(
    val subTaskItemClickListener: (subTask: SubTask, task: Task) -> Unit
) {
    fun onSubTaskClick(subTask: SubTask, task: Task) = subTaskItemClickListener(subTask, task)

}
