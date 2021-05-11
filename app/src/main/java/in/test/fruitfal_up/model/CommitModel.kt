package `in`.test.fruitfal_up.model

import com.google.gson.annotations.SerializedName

class CommitModel(
    @SerializedName("author")
    var author: AuthorModel? = null,

    @SerializedName("message")
    var commitMessage: String = "",

    @SerializedName("url")
    var commitUrl: String = "",

    @SerializedName("comment_count")
    var commentCount: Int = 0

)