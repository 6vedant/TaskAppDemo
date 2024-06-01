package io.scade.taskappdemo.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import io.scade.taskappdemo.databinding.TaskRecyclerItemBinding
import io.scade.taskappdemo.model.Task

public class TaskRecyclerAdapter constructor(
    val tasks: List<Task>,
    val clickListener: TaskItemClickListener
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
        holder.bind(tasks[position], clickListener)
    }

    class ItemViewHolder constructor(private val binding: TaskRecyclerItemBinding) :
        ViewHolder(binding.root) {
        fun bind(item: Task?, clickListener: TaskItemClickListener) {
            item.let {
                binding.task = it
                binding.clickListener = clickListener
            }
        }
    }
}

class TaskItemClickListener(val itemClickListener: (task: Task) -> Unit, val subTaskTextClickListener: (task: Task) -> Unit ) {
    fun onItemClick(task: Task) = itemClickListener(task)
    fun onSubTaskClick(task: Task) = subTaskTextClickListener(task)

}
