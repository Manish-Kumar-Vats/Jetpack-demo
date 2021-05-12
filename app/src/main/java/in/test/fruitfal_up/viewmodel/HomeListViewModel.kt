package `in`.test.fruitfal_up.viewmodel

import `in`.test.fruitfal_up.network.ApiService
import `in`.test.fruitfal_up.network.CommitPagingSource
import `in`.test.fruitfal_up.network.RetroClient
import `in`.test.fruitfal_up.response.CommitResponse
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import kotlinx.coroutines.flow.Flow

class HomeListViewModel : ViewModel() {



    private val _navigateToDetail = MutableLiveData<String?>()
    val navigateToDetail
        get() = _navigateToDetail

    fun onViewClick(sha: String) {
        _navigateToDetail.value = sha
    }

    fun onNavigated() {
        _navigateToDetail.value = null
    }

    lateinit var retroService: ApiService

    init {
        retroService = RetroClient.getRetroInstance().create(ApiService::class.java)
    }

    fun getPagingList(): Flow<PagingData<CommitResponse>> {

        return Pager(config = PagingConfig(pageSize = 10, maxSize = 200),
            pagingSourceFactory = { CommitPagingSource(retroService) }).flow.cachedIn(viewModelScope)
    }


}