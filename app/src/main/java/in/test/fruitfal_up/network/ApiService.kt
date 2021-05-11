package `in`.test.fruitfal_up.network

import `in`.test.fruitfal_up.response.CommitResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query


interface ApiService {

    // Get Commit listing
    @GET("repos/{owner}/{repo}/commits")
    fun getCommitListing(
        @Path("owner") owner: String,
        @Path("repo") repo: String,
        @Header("Authorization") auth: String,
        @Query("per_page") perPage: String,
        @Query("page") page: String
    ): Call<List<CommitResponse>>

    // Get Commit listing
    @GET("repos/{owner}/{repo}/commits/{ref}")
    fun getCommitDetail(
        @Path("owner") owner: String,
        @Path("repo") repo: String,
        @Header("Authorization") auth: String,
        @Path("ref") ref: String
    ): Call<CommitResponse>

    /*
    companion object {
        private const val BASE_URL = "https://api.github.com/"

        fun create(): ApiService {
            val logger = HttpLoggingInterceptor()
            logger.level = HttpLoggingInterceptor.Level.BASIC

            val client = OkHttpClient.Builder()
                .addInterceptor(logger)
                .build()
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiService::class.java)
        }
    }*/
}
