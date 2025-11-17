package com.example.lab_week_10.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * ViewModel untuk mengelola data total counter dengan LiveData
 */
class TotalViewModel : ViewModel() {

    private val _total = MutableLiveData<Int>()
    val total: LiveData<Int> = _total

    init {
        _total.postValue(0)
    }

    /**
     * Increment nilai total
     */
    fun incrementTotal() {
        _total.postValue(_total.value?.plus(1))
    }

    /**
     * Set nilai total dari database atau sumber lain
     */
    fun setTotal(newTotal: Int) {
        _total.postValue(newTotal)
    }
}
