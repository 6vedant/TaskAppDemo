package io.scade.taskappdemo.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import io.scade.taskappdemo.databinding.FragmentCreateSubtaskBinding
import io.scade.taskappdemo.databinding.FragmentCreateTaskBinding
import io.scade.taskappdemo.databinding.FragmentUpdateTaskBinding

class CreateSubTaskFragment: Fragment() {
    private lateinit var binding: FragmentCreateSubtaskBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCreateSubtaskBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}