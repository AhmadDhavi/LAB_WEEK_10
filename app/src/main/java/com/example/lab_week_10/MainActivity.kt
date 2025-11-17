package com.example.lab_week_10

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.lab_week_10.viewmodels.TotalViewModel

class MainActivity : AppCompatActivity() {

    // Inisialisasi ViewModel menggunakan lazy delegate
    private val viewModel by lazy {
        ViewModelProvider(this)[TotalViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        prepareViewModel()
    }

    /**
     * Update text pada TextView sesuai nilai total
     */
    private fun updateText(total: Int) {
        findViewById<TextView>(R.id.text_total).text =
            getString(R.string.text_total, total)
    }

    /**
     * Setup observer untuk ViewModel dan click listener untuk button
     */
    private fun prepareViewModel() {
        // Update UI dengan nilai awal dari ViewModel
        updateText(viewModel.total)

        // Set click listener untuk increment button
        findViewById<Button>(R.id.button_increment).setOnClickListener {
            updateText(viewModel.incrementTotal())
        }
    }
}
