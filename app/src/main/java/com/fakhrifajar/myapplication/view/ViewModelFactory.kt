package com.fakhrifajar.myapplication.view

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.fakhrifajar.myapplication.di.Injection
import com.fakhrifajar.myapplication.repository.UserRepository
import com.fakhrifajar.myapplication.view.dashboard.SharedViewModel
import com.fakhrifajar.myapplication.view.dashboard.ThirdScreenViewModel

class ViewModelFactory private constructor(
    private val userRepository: UserRepository,
    private val sharedViewModel: SharedViewModel
): ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ThirdScreenViewModel::class.java)) {
            return ThirdScreenViewModel(userRepository) as T
        }

        if (modelClass.isAssignableFrom(SharedViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return sharedViewModel as T
        }

        throw IllegalArgumentException("Unknown Viewmodel Class: " + modelClass.name)
    }

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null
        private var sharedViewModel: SharedViewModel? = null

        fun getInstance(context: Context): ViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: ViewModelFactory(
                    Injection.provideRepository(context),
                    sharedViewModel ?: SharedViewModel().also { sharedViewModel = it }
                )
            }
    }
}
