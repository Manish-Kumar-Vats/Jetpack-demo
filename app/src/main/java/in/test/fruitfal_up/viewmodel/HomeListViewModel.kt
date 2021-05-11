package `in`.test.fruitfal_up.viewmodel

import `in`.test.fruitfal_up.network.ApiClient
import `in`.test.fruitfal_up.network.ApiService
import `in`.test.fruitfal_up.repository.CommitRepository
import `in`.test.fruitfal_up.response.CommitResponse
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import retrofit2.Callback
import retrofit2.Response

class HomeListViewModel : ViewModel() {

    companion object {
        private const val VISIBLE_THRESHOLD = 5
        var pageToLoad = "1"
    }

    val isLoaded = MutableLiveData<Boolean>()

    val isScrolledEnd = MutableLiveData<Boolean>()

    private var _commitList = MutableLiveData<List<CommitResponse>>()

    val commitList = _commitList

    private val _navigateToDetail = MutableLiveData<String?>()
    val navigateToDetail
        get() = _navigateToDetail

    fun onViewClick(sha: String) {
        _navigateToDetail.value = sha
    }

    fun onNavigated() {
        _navigateToDetail.value = null
    }


    init {
        isLoaded.value = false
        isScrolledEnd.value = false
        getCommitListing(pageToLoad)

    }

    private fun getCommitListing(pageNumber: String) {
        ApiClient.getClient().create(ApiService::class.java)
            .getCommitListing("square", "retrofit", "10", "1")
            .enqueue(object : Callback<List<CommitResponse>> {
                override fun onResponse(
                    call: retrofit2.Call<List<CommitResponse>>,
                    response: Response<List<CommitResponse>>
                ) {
                    val tempList = ArrayList<CommitResponse>()
                    if (_commitList.value != null && _commitList.value!!.isNotEmpty()) {
                        Log.i("TAG","1")
                        tempList.addAll(_commitList.value!!)
                        tempList.addAll(response.body()!!)
                        _commitList.value = tempList
                        pageToLoad += 1
                    }else{
                        Log.i("TAG","2")
                        tempList.addAll(response.body()!!)
                        _commitList.value = tempList
                        pageToLoad += 1
                        Log.i("TAG","13"+response.errorBody().toString())
                        Log.i("TAG","23"+response.message())
                        Log.i("TAG","33"+response.body())
                    }
                    isLoaded.value = true
                }

                override fun onFailure(call: retrofit2.Call<List<CommitResponse>>, t: Throwable) {
//                    _commitList.value = emptyList()
                    isLoaded.value = true
                }

            })
    }

//    fun listScrolled(visibleItemCount: Int, lastVisibleItemPosition: Int, totalItemCount: Int) {
//        if (visibleItemCount + lastVisibleItemPosition + VISIBLE_THRESHOLD >= totalItemCount) {
//            viewModelScope.launch {
//                getCommitListing(pageToLoad)
//            }
//        }
//    }
}