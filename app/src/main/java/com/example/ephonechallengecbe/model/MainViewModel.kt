package com.example.ephonechallengecbe.model

import com.example.ephonechallengecbe.data.ParsedSmsDataClass
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ephonechallengecbe.data.SmsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel : ViewModel() {
    private val _smsData = MutableLiveData<List<ParsedSmsDataClass>>()
    val smsData: LiveData<List<ParsedSmsDataClass>> = _smsData

    fun loadSmsData(repository: SmsRepository) {
        viewModelScope.launch {
            val data = withContext(Dispatchers.IO) {
                repository.getLatestSms()
            }
            _smsData.postValue(data)
        }
    }
}
