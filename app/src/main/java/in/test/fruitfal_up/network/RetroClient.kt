package `in`.test.fruitfal_up.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetroClient {
        companion object {
            val baseURL = "https://api.github.com/"

            fun getRetroInstance(): Retrofit {
                return Retrofit.Builder()
                    .baseUrl(baseURL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()

            }
        }

}