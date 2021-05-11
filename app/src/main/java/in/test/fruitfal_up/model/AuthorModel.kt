package `in`.test.fruitfal_up.model

import com.google.gson.annotations.SerializedName

class AuthorModel(
    @SerializedName("name")
    var authorName: String = "",

    @SerializedName("email")
    var authorEmail: String = "",

    @SerializedName("date")
    var commitDate: String = ""
)