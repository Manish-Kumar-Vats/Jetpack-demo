package `in`.test.fruitfal_up.response

import `in`.test.fruitfal_up.model.CommitModel
import `in`.test.fruitfal_up.model.FilesModel
import `in`.test.fruitfal_up.model.StatsModel
import com.google.gson.annotations.SerializedName


data class CommitResponse(
    val sha: String = "",
    val url: String = "",
    @SerializedName("html_url")
    val htmlUrl: String = "",
    @SerializedName("comments_url")
    val commentsUrl: String = "",
    val commit: CommitModel? = null,
    val stats: StatsModel? = null,
    val files: List<FilesModel> = emptyList()
)

/*

class CommitsResponse() : Parcelable {

    @SerializedName("sha")
    @Expose
    var sha: String = ""

    @SerializedName("url")
    @Expose
    var url: String = ""

    @SerializedName("html_url")
    @Expose
    var htmlUrl: String = ""

    @SerializedName("comments_url")

    @Expose
    var commentsUrl: String = ""

    @SerializedName("commit")
    @Expose
    var commitData: CommitModel? = null

    @SerializedName("stats")
    @Expose
    var commitStats: StatsModel? = null


    @SerializedName("files")
    @Expose
    var commitFiles: List<FilesModel> = ArrayList()

}*/
