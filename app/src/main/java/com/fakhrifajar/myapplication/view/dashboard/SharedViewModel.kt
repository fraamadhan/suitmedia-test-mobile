package com.fakhrifajar.myapplication.view.dashboard

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedViewModel : ViewModel() {
    private val _selectedUserName = MutableLiveData<String>()
    val selectedUserName: LiveData<String> get() = _selectedUserName

    fun setSelectedUserName(name: String) {
        Log.d("INI NAMAKUYA", name)
        _selectedUserName.value = name
    }
}