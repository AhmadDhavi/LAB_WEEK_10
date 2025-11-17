package com.example.lab_week_10

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.lab_week_10.viewmodels.TotalViewModel

class MainActivity : AppCompatActivity() {

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
     * Observe LiveData dan setup click listener
     * Setiap kali nilai LiveData berubah, updateText akan dipanggil
     */
    private fun prepareViewModel() {
        // Observe LiveData object
        viewModel.total.observe(this) {
            // Ketika nilai LiveData berubah, updateText dipanggil dengan nilai baru
            updateText(it)
        }

        findViewById<Button>(R.id.button_increment).setOnClickListener {
            viewModel.incrementTotal()
        }
    }
}
