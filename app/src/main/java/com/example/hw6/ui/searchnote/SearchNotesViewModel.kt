package com.example.hw6.ui.searchnote

import androidx.lifecycle.*
import com.example.hw6.domain.SearchNotesUseCase
import com.example.hw6.model.Note
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class SearchNotesViewModel(
    private val searchNotesUseCase: SearchNotesUseCase = SearchNotesUseCase()
) : ViewModel() {

    private val _notesLiveData = MutableLiveData<List<Note>>()
    val notesLiveData: LiveData<List<Note>> = _notesLiveData

    fun searchNotes(search: String) {
        viewModelScope.launch {
            searchNotesUseCase("%$search%").collect {   list->
                _notesLiveData.value = list
            }
        }
    }
}