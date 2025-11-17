package com.example.lab_week_10

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.example.lab_week_10.database.Total
import com.example.lab_week_10.database.TotalObject
import com.example.lab_week_10.database.TotalDatabase
import com.example.lab_week_10.viewmodels.TotalViewModel
import java.util.Date

/**
 * Main Activity untuk aplikasi counter dengan Room Database dan timestamp tracking
 * Menampilkan kapan terakhir kali nilai counter di-update
 */
class MainActivity : AppCompatActivity() {

    /**
     * Database instance - lazy loading untuk inisialisasi saat pertama kali digunakan
     */
    private val db by lazy {
        prepareDatabase()
    }

    /**
     * ViewModel instance - shared dengan Fragment untuk sinkronisasi data
     */
    private val viewModel by lazy {
        ViewModelProvider(this)[TotalViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Load nilai total dari database saat activity dibuat
        initializeValueFromDatabase()

        // Setup observer ViewModel dan button click listener
        prepareViewModel()

        // Tampilkan timestamp di onStart
        showLastUpdateTimestamp()
    }

    override fun onStart() {
        super.onStart()
        // Tampilkan kapan terakhir kali counter di-update
        showLastUpdateTimestamp()
    }

    /**
     * Inisialisasi nilai total dari database atau insert nilai default jika kosong
     */
    private fun initializeValueFromDatabase() {
        // Query nilai total dengan ID = 1
        val totalList = db.totalDao().getTotal(ID)

        if (totalList.isEmpty()) {
            // Database kosong, insert nilai awal 0 dengan timestamp saat ini
            db.totalDao().insert(
                Total(
                    id = ID,
                    total = TotalObject(
                        value = 0,
                        date = Date().toString()
                    )
                )
            )
            viewModel.setTotal(0)
        } else {
            // Database ada data, ambil nilai total
            val savedTotal = totalList.first().total.value
            viewModel.setTotal(savedTotal)
        }
    }

    /**
     * Setup observer LiveData dan button click listener
     */
    private fun prepareViewModel() {
        // Observe perubahan total value dari ViewModel
        viewModel.total.observe(this) { newTotal ->
            updateText(newTotal)
        }

        // Setup button increment listener
        findViewById<Button>(R.id.button_increment).setOnClickListener {
            viewModel.incrementTotal()
        }
    }

    /**
     * Update TextView dengan nilai total terbaru
     */
    private fun updateText(total: Int) {
        findViewById<TextView>(R.id.text_total).text =
            getString(R.string.text_total, total)
    }

    /**
     * Buat dan konfigurasi Room Database instance
     */
    private fun prepareDatabase(): TotalDatabase {
        return Room.databaseBuilder(
            applicationContext,
            TotalDatabase::class.java,
            "total-database"
        )
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()  // Destructive migration untuk development
            .build()
    }


    /**
     * Tampilkan timestamp terakhir kali counter di-update
     */
    private fun showLastUpdateTimestamp() {
        val totalList = db.totalDao().getTotal(ID)

        if (totalList.isNotEmpty()) {
            val lastUpdateDate = totalList.first().total.date
            Toast.makeText(
                this,
                "Last update: $lastUpdateDate",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    /**
     * Simpan nilai counter ke database dan update timestamp saat activity di-pause
     */
    override fun onPause() {
        super.onPause()
        val currentTotal = viewModel.total.value ?: 0

        db.totalDao().update(
            Total(
                id = ID,
                total = TotalObject(
                    value = currentTotal,
                    date = Date().toString() // Update timestamp dengan waktu sekarang
                )
            )
        )
    }

    companion object {
        // ID untuk data di database (digunakan sebagai primary key)
        private const val ID: Long = 1L
    }
}
