package com.fakhrifajar.myapplication.repository

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.fakhrifajar.myapplication.data.UserPagingSource
import com.fakhrifajar.myapplication.data.network.api.ApiService
import com.fakhrifajar.myapplication.data.network.response.DataItem

class UserRepository(private val apiService : ApiService) {
    fun getUsers(): LiveData<PagingData<DataItem>> {
        return Pager(
            config = PagingConfig(
                pageSize = 10,
                prefetchDistance = 10,
                initialLoadSize = 10,
                maxSize = PagingConfig.MAX_SIZE_UNBOUNDED,
            ),
            pagingSourceFactory = {
                UserPagingSource(apiService)
            }
        ).liveData
    }

    companion object {
        @Volatile
        private var INSTANCE: UserRepository? = null

        @JvmStatic
        fun getInstance(
            apiService: ApiService,
        ): UserRepository =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: UserRepository(apiService)
            }.also { INSTANCE = it }
    }

}