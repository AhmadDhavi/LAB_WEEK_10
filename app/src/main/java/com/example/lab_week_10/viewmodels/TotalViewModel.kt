package com.example.lab_week_10.viewmodels

import androidx.lifecycle.ViewModel

/**
 * ViewModel untuk menyimpan dan mengelola data total counter
 * Data akan tetap tersimpan meskipun activity di-recreate
 */
class TotalViewModel : ViewModel() {
    var total: Int = 0

    /**
     * Increment nilai total dan return nilai baru
     */
    fun incrementTotal(): Int {
        total++
        return total
    }
}
