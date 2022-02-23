package com.xlwe.lectures

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {

    private val repository = RepositoryImpl()
    private val _listNumbers: MutableLiveData<List<Int>> = MutableLiveData(repository.update())
    val listNumbers: LiveData<List<Int>> get() = _listNumbers

    fun delete(position: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.delete(position)
            _listNumbers.postValue(repository.update())
        }
    }
}