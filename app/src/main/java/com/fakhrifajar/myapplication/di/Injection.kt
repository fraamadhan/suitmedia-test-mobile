package com.fakhrifajar.myapplication.di

import android.content.Context
import com.fakhrifajar.myapplication.data.network.api.ApiConfig
import com.fakhrifajar.myapplication.repository.UserRepository

object Injection {
    fun provideRepository(context: Context): UserRepository {
        val apiService = ApiConfig.getApiService()
        return UserRepository.getInstance(apiService)
    }
}