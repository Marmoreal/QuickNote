package com.example.hw6.ui.listnotes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.hw6.R
import com.example.hw6.databinding.FragmentListNotesBinding
import com.google.android.material.transition.MaterialElevationScale

class ListNotesFragment : Fragment() {

    private lateinit var binding: FragmentListNotesBinding
    private val noteAdapter = NoteAdapter()
    private val viewModel: ListNotesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        exitTransition = MaterialElevationScale(false)
        reenterTransition = MaterialElevationScale(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListNotesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.toolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.searchFragment -> {
                    findNavController().navigate(R.id.action_listNotesFragment_to_searchFragment)
                    true
                }
                else -> false
            }
        }

        binding.grid.apply {
            layoutManager = StaggeredGridLayoutManager(2, RecyclerView.VERTICAL)
            adapter = noteAdapter
                .apply {
                    setOnItemLongClick {
                        viewModel.deleteNotes(it)
                    }
                }
        }

        viewModel.notesLiveData.observe(viewLifecycleOwner) {
            noteAdapter.submitList(it)
        }

        binding.fab.setOnClickListener {
            val extras = FragmentNavigatorExtras(view to "shared_element_container")
            findNavController().navigate(
                R.id.action_listNotesFragment_to_addNoteFragment,
                null,
                null,
                extras
            )
        }
    }
}