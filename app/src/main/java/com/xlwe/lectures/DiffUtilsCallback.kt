package com.xlwe.lectures

import android.util.Log
import androidx.recyclerview.widget.DiffUtil

class DiffUtilsCallback(
    private val oldList: List<Note>,
    private val newList: List<Note>
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        Log.d("TEST1", "${oldList[oldItemPosition]}   ${newList[newItemPosition]}")
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        Log.d("TEST2", "${oldList[oldItemPosition]}   ${newList[newItemPosition]}")
        return oldList == newList
    }

}