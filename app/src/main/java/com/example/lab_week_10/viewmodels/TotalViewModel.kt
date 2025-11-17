package com.example.lab_week_10.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * ViewModel untuk menyimpan dan mengelola data total counter
 * Menggunakan LiveData untuk observable pattern
 */
class TotalViewModel : ViewModel() {

    // Declare LiveData object sebagai private untuk encapsulation
    private val _total = MutableLiveData<Int>()

    // Expose sebagai LiveData (read-only) untuk observer
    val total: LiveData<Int> = _total

    init {
        // postValue digunakan untuk set value dari background thread atau main thread
        // setValue hanya bisa digunakan dari main thread
        _total.postValue(0)
    }

    /**
     * Increment nilai total dan update LiveData
     */
    fun incrementTotal() {
        _total.postValue(_total.value?.plus(1))
    }
}
