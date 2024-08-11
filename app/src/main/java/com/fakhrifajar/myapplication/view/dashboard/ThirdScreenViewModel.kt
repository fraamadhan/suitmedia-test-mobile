package com.fakhrifajar.myapplication.view.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.fakhrifajar.myapplication.data.network.response.DataItem
import com.fakhrifajar.myapplication.repository.UserRepository

class ThirdScreenViewModel(private val userRepository: UserRepository) : ViewModel() {
    val users : LiveData<PagingData<DataItem>> = userRepository.getUsers().cachedIn(viewModelScope)

}