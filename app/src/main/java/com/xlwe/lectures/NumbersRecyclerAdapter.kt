package com.xlwe.lectures

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.xlwe.lectures.databinding.ItemBinding

class NumbersRecyclerAdapter(
    private var numbers: List<Int>,
    private val onClickListener: OnClickListener
) : RecyclerView.Adapter<NumbersViewHolder>() {

    fun update(newList: List<Int>) {
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
            binding.textTv.text = item.toString()

            itemView.setOnClickListener {
                onClickListener.onClick(position)
            }
        }
    }

    override fun getItemCount(): Int {
        return numbers.size
    }

}