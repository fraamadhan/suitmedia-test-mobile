package com.fakhrifajar.myapplication.data

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.fakhrifajar.myapplication.data.network.api.ApiService
import com.fakhrifajar.myapplication.data.network.response.DataItem

class UserPagingSource(private val apiService: ApiService): PagingSource<Int, DataItem>() {
    private companion object {
        const val INITIAL_PAGE_INDEX = 1
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, DataItem> {
        return try {
            val position = params.key ?: INITIAL_PAGE_INDEX
            Log.d("PagingSource", "Fetching page: $position with load size: ${params.loadSize}")
            val response = apiService.getUsers(position, params.loadSize)
            val responseData = response.data?.filterNotNull() ?: emptyList() // Handle null values

            Log.d("PagingSource", "Received data: $responseData")
            LoadResult.Page(
                data = responseData,
                prevKey = if (position == INITIAL_PAGE_INDEX) null else position - 1,
                nextKey = if (responseData.isEmpty()) null else position + 1
            )
        } catch (exception: Exception) {
            Log.e("PagingSource", "Error fetching data", exception)
            LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, DataItem>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}
