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

  /*  fun getCommitListing(pageNumber: String): LiveData<List<CommitResponse>> {
        val data: MutableLiveData<List<CommitResponse>> =
            MutableLiveData()
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
                        val list = ArrayList<CommitResponse>()
                        _commitList.value?.let { list.addAll(it) }
                        response.body()?.let { list.addAll(it) }

//                        _commitList.value = response.body()
                        _commitList.value = list


                }

                override fun onFailure(call: retrofit2.Call<List<CommitResponse>>, t: Throwable) {

                }

            })
    }*/
//
//    fun getCommitListing(pageNumber: String): LiveData<List<CommitResponse>> {
//        val data: MutableLiveData<List<CommitResponse>> =
//            MutableLiveData()
//        apiService .getCommitListing(
//            "square",
//            "retrofit",
//            "token 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",
//            "10",
//            pageNumber
//        ).enqueue(object : Callback<List<CommitResponse>> {
//                override fun onResponse(
//                    call: retrofit2.Call<List<CommitResponse>>,
//                    response: Response<List<CommitResponse>>
//                ) {
//                    data.value = response.body()
//                }
//
//                override fun onFailure(call: retrofit2.Call<List<CommitResponse>>, t: Throwable) {
//                    data.value = null
//                }
//
//            })
//        return data
//    }

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