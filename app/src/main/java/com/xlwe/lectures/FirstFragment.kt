package com.xlwe.lectures

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentContainerView
import androidx.fragment.app.viewModels

class FirstFragment : Fragment() {
    private val viewModel: ItemViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<FragmentContainerView>(R.id.fragmentContainer2).setOnClickListener {
            viewModel.selectedItem(20)

            childFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer2, SecondFragment())
                .addToBackStack(null)
                .commit()
        }
    }

    private fun log(message: String) {
        Log.d("log", message)
    }
}