package io.scade.taskappdemo.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import io.scade.taskappdemo.databinding.FragmentCreateTaskBinding
import io.scade.taskappdemo.databinding.FragmentDisplayTaskBinding
import io.scade.taskappdemo.model.Task

class DisplayTaskFragment : Fragment() {
    private lateinit var binding: FragmentDisplayTaskBinding


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
            this.findNavController().navigate(
                DisplayTaskFragmentDirections.actionNavigateToCreateTaskFragment()
            )
        }
    }
}