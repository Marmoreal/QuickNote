package com.example.hw6.ui.searchnote

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.hw6.databinding.FragmentSearchNotesBinding
import com.example.hw6.ui.listnotes.NoteAdapter

class SearchNotesFragment : Fragment() {

    private lateinit var binding: FragmentSearchNotesBinding
    private val viewModel : SearchNotesViewModel by viewModels()
    private val noteAdapter = NoteAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchNotesBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.toolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }

        binding.tiSearchLine.doAfterTextChanged {
            viewModel.searchNotes(it?.toString() ?: "")
        }

        binding.searchNotesList.apply {
            layoutManager = StaggeredGridLayoutManager(2, RecyclerView.VERTICAL)
            adapter = noteAdapter

            viewModel.notesLiveData.observe(viewLifecycleOwner) {
                noteAdapter.submitList(it)
            }
        }
    }
}