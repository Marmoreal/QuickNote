package com.example.hw6.ui.addnote

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.hw6.databinding.FragmentAddNoteBinding
import com.google.android.material.snackbar.Snackbar

class AddNoteFragment : Fragment() {

    private lateinit var binding: FragmentAddNoteBinding
    private val viewModel: AddNoteViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddNoteBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.navigateLiveData.observe(viewLifecycleOwner) {
            findNavController().popBackStack()
        }

        viewModel.snackLiveData.observe(viewLifecycleOwner){
            Snackbar.make(binding.root,"Note is empty",Snackbar.LENGTH_SHORT).show()
        }

        binding.fab.setOnClickListener {
            viewModel.addNote(binding.textInput.text.toString())
        }

        binding.toolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
    }
}