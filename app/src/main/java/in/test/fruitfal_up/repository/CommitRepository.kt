package `in`.test.fruitfal_up.repository

import `in`.test.fruitfal_up.network.ApiClient
import `in`.test.fruitfal_up.network.ApiService
import `in`.test.fruitfal_up.response.CommitResponse
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CommitRepository {
    private var apiService: ApiService = ApiClient.getClient().create(ApiService::class.java)

    fun getCommitListing(
        owner: String,
        repo: String,
        perPage: String,
        page: String
    ): LiveData<List<CommitResponse>> {
        val data: MutableLiveData<List<CommitResponse>> =
            MutableLiveData()
        apiService.getCommitListing(owner, repo,"token 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", perPage, page)
            .enqueue(object : Callback<List<CommitResponse>> {
                override fun onResponse(
                    call: retrofit2.Call<List<CommitResponse>>,
                    response: Response<List<CommitResponse>>
                ) {
                    data.value = response.body()
                }

                override fun onFailure(call: retrofit2.Call<List<CommitResponse>>, t: Throwable) {
                    data.value = null
                }

            })
        return data
    }

    fun getCommitDetail(
        owner: String,
        repo: String,
        sha: String
    ): LiveData<CommitResponse> {
        val data: MutableLiveData<CommitResponse> =
            MutableLiveData()
        apiService.getCommitDetail(owner, repo,"token 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", sha)
            .enqueue(object : Callback<CommitResponse> {
                override fun onResponse(
                    call: Call<CommitResponse>,
                    response: Response<CommitResponse>
                ) {
                    data.value = response.body()
                }

                override fun onFailure(call: Call<CommitResponse>, t: Throwable) {
                    data.value = null
                }

            })
        return data
    }
}