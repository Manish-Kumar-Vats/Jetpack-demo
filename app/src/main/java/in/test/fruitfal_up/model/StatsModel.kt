package `in`.test.fruitfal_up.model

import com.google.gson.annotations.SerializedName

data class StatsModel(
    @SerializedName("total")
    var total: String = "",

    @SerializedName("additions")
    var additions: String = "",

    @SerializedName("deletions")
    var deletions: String = ""

)