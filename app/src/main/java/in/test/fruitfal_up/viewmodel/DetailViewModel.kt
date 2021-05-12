package `in`.test.fruitfal_up.viewmodel

import `in`.test.fruitfal_up.network.ApiClient
import `in`.test.fruitfal_up.network.ApiService
import `in`.test.fruitfal_up.network.RetroClient
import `in`.test.fruitfal_up.response.CommitResponse
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailViewModel(val sha: String) : ViewModel() {

    private val _authorName = MutableLiveData<String>()
    val authorName: LiveData<String>
        get() = _authorName

    private val _sha = MutableLiveData<String>()
    val shavalue: LiveData<String>
        get() = _sha

    private val _commitMessage = MutableLiveData<String>()
    val commitMessage: LiveData<String>
        get() = _commitMessage

    private val _commitDate = MutableLiveData<String>()
    val commitDate: LiveData<String>
        get() = _commitDate

    private val _addition = MutableLiveData<String>()
    val addition: LiveData<String>
        get() = _addition

    private val _deletion = MutableLiveData<String>()
    val deletion: LiveData<String>
        get() = _deletion

    private val _total = MutableLiveData<String>()
    val total: LiveData<String>
        get() = _total

    private val _files = MutableLiveData<Int>()
    val files: LiveData<Int>
        get() = _files


    init {
        _sha.value = sha
        getCommitDetail()
    }

    private fun getCommitDetail() {

     /*
        val commitsDetail = RetroClient.getRetroInstance().create(ApiService::class.java)
            .getCommitDetail(
                "token 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",
                sha
            )

*/
        ApiClient.getClient().create(ApiService::class.java)
            .getCommitDetail(
                "square",
                "retrofit",
                "token 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",
                sha
            )
            .enqueue(object : Callback<CommitResponse> {
                override fun onResponse(
                    call: Call<CommitResponse>,
                    response: Response<CommitResponse>
                ) {
                    val _CommitDetail: CommitResponse = response.body()!!
                    val commitObj = _CommitDetail.commit
                    _commitMessage.value = commitObj?.commitMessage
                    _authorName.value = commitObj?.author?.authorName
                    _commitDate.value = commitObj?.author?.commitDate
                    Log.i("TAG", commitObj.toString())
                    _addition.value = _CommitDetail.stats?.additions
                    _deletion.value = _CommitDetail.stats?.deletions
                    _total.value = _CommitDetail.stats?.total

                    _files.value = _CommitDetail.files.size

                }

                override fun onFailure(call: Call<CommitResponse>, t: Throwable) {
//                    data.value = null
                }

            })
    }

}