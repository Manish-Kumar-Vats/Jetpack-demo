package `in`.test.fruitfal_up.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


interface ApiService {

    //
//    @GET("search/repositories?sort=stars")
//    suspend fun searchRepos(
//        @Query("q") query: String,
//        @Query("page") page: Int,
//        @Query("per_page") itemsPerPage: Int
//    ): RepoSearchResponse
//
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
    }
}
