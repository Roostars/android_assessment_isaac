package com.example.prueba.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.prueba.data.model.Facts
import com.example.prueba.data.model.UiState
import com.example.prueba.repository.FactsRepository
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    private val repository = FactsRepository()

    private val _facts = MutableLiveData<UiState<List<Facts>>>()
    val facts: LiveData<UiState<List<Facts>>> get() = _facts

    private var currentPage = 0
    private val pageSize = 10
    var query = ""

    fun getAllFacts(){
        viewModelScope.launch {
            val facts = repository.getFactsFromDatabase(pageSize, 0)
            _facts.value = UiState.Success(facts)
        }
    }

    fun loadNextPage() {
        viewModelScope.launch {
            val offset = currentPage * pageSize
            val facts = if (query.isNotEmpty())
                repository.getFilterFactsFromDatabase(query.toUpperCase(), pageSize, offset)
            else
                repository.getFactsFromDatabase(pageSize, offset)
            currentPage++
            _facts.value = UiState.Success(facts)
        }
    }

    fun loadPreviousPage() {
        viewModelScope.launch {
            if (currentPage > 0) {
                currentPage--
                val offset = currentPage * pageSize
                val facts = if (query.isNotEmpty())
                    repository.getFilterFactsFromDatabase(query.toUpperCase(), pageSize, offset)
                else
                    repository.getFactsFromDatabase(pageSize, offset)
                _facts.value = UiState.Success(facts)
            }
        }
    }

}