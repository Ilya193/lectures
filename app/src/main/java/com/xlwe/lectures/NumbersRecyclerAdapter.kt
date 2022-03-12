package com.xlwe.lectures

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.xlwe.lectures.databinding.ItemBinding

class NumbersRecyclerAdapter(
    private var numbers: List<Note>,
    private val onClickListener: OnClickListener
) : RecyclerView.Adapter<NumbersViewHolder>() {

    fun update(newList: List<Note>) {
        val diffUtilsCallback = DiffUtilsCallback(numbers, newList)
        val diffResult = DiffUtil.calculateDiff(diffUtilsCallback)
        numbers = newList
        diffResult.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NumbersViewHolder {
        return NumbersViewHolder(
            ItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: NumbersViewHolder, position: Int) {
        val item = numbers[position]

        with(holder) {
            binding.textTv.text = item.text

            itemView.setOnClickListener {
                onClickListener.onClick(adapterPosition)
            }
        }
    }

    override fun getItemCount(): Int {
        return numbers.size
    }

}