package com.xlwe.lectures

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel(
    private val repository: Repository
) : ViewModel() {

    private val _countValue: MutableLiveData<Resource> =
        MutableLiveData()
    val countValue get() = _countValue


    fun getCount() {
        repository.getCount(object : ResultCallback<Resource> {
            override fun provideInfo(info: Resource) {
                _countValue.postValue(info)
            }
        })

        /*_countValue.value = Resource.Loading()
        _countValue.value = Resource.Success(repository.count.toString())*/
    }

}