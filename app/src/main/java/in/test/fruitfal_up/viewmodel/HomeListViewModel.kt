package `in`.test.fruitfal_up.viewmodel

import `in`.test.fruitfal_up.network.ApiClient
import `in`.test.fruitfal_up.network.ApiService
import `in`.test.fruitfal_up.response.CommitResponse
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Callback
import retrofit2.Response

class HomeListViewModel : ViewModel() {

    val isLoadedFirstTime = MutableLiveData<Boolean>()
    var isScrolling = MutableLiveData<Boolean>()
    var pageToLoad = "1"

    val isDataAvailable = MutableLiveData<Boolean>()

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
        isDataAvailable.value = false
        isLoadedFirstTime.value = false
        isScrolling.value = true
        getCommitListing(pageToLoad)

    }

    private fun getCommitListing(pageNumber: String) {
        ApiClient.getClient().create(ApiService::class.java)
            .getCommitListing(
                "square",
                "retrofit",
                "token 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",
                "10",
                pageNumber
            )
            .enqueue(object : Callback<List<CommitResponse>> {
                override fun onResponse(
                    call: retrofit2.Call<List<CommitResponse>>,
                    response: Response<List<CommitResponse>>
                ) {
                    try {
                        val list = ArrayList<CommitResponse>()
                        _commitList.value?.let { list.addAll(it) }
                        response.body()?.let { list.addAll(it) }

//                        _commitList.value = response.body()
                        _commitList.value = list
                        isDataAvailable.value = true
                        pageToLoad += 1

                    } catch (e: Exception) {
                        isDataAvailable.value = false
                        Log.i("TAG", e.toString())
                    }
                    isScrolling.value = true
                    isLoadedFirstTime.value = true
                }

                override fun onFailure(call: retrofit2.Call<List<CommitResponse>>, t: Throwable) {
                    isLoadedFirstTime.value = true
                    isDataAvailable.value = false
                }

            })
    }

    fun listScrolled(visibleItemCount: Int, lastVisibleItemPosition: Int, totalItemCount: Int) {
        if (visibleItemCount + lastVisibleItemPosition + 1 >= totalItemCount) {
            isScrolling.value = false

            getCommitListing(pageToLoad)

        }
    }
}