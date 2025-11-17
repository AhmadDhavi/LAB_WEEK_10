package com.example.lab_week_10

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.example.lab_week_10.viewmodels.TotalViewModel

/**
 * Fragment untuk menampilkan total counter
 * Menggunakan ViewModel yang sama dengan MainActivity
 */
class FirstFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        prepareViewModel()
    }

    /**
     * Update text pada TextView sesuai nilai total
     */
    private fun updateText(total: Int) {
        view?.findViewById<TextView>(R.id.text_total)?.text =
            getString(R.string.text_total, total)
    }

    /**
     * Setup ViewModel dengan activity scope untuk shared data
     */
    private fun prepareViewModel() {
        val viewModel = ViewModelProvider(requireActivity())[TotalViewModel::class.java]
        viewModel.total.observe(viewLifecycleOwner) {
            updateText(it)
        }
    }

    companion object {
        fun newInstance(param1: String, param2: String) = FirstFragment()
    }
}
