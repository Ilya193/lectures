package com.xlwe.lectures

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ItemViewModel : ViewModel() {
    private val _selectedItem = MutableLiveData<Int>()
    val selectedItem: LiveData<Int>
        get() = _selectedItem

    fun selectedItem(num: Int) {
        _selectedItem.value = num
    }
}