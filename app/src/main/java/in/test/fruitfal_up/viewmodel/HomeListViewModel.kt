package `in`.test.fruitfal_up.viewmodel

import `in`.test.fruitfal_up.model.AuthorModel
import `in`.test.fruitfal_up.model.CommitModel
import `in`.test.fruitfal_up.model.FilesModel
import `in`.test.fruitfal_up.model.StatsModel
import `in`.test.fruitfal_up.response.CommitResponse
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeListViewModel : ViewModel() {


    private var _commitList = MutableLiveData<List<CommitResponse>>()

    val commitList = _commitList

    private val _navigateToDetail = MutableLiveData<String?>()
    val navigateToDetail
        get() = _navigateToDetail

    fun onViewClick(sha: String) {
        _navigateToDetail.value = sha
    }

    fun onNavigated() {
        _navigateToDetail.value = null
    }


    init {
        val author = AuthorModel("authorName", "authorEmail", "date")
        val commit = CommitModel(author, "commitMessage", "URL", 4)
        val stats = StatsModel("40", "32", "8")
        val file = FilesModel("fileStatus")
        val files = ArrayList<FilesModel>()
        files.add(file)
        val list = ArrayList<CommitResponse>()
        list.add(CommitResponse("1212", "URL", "htmlURL", "commentsURL", commit, stats, files))
        _commitList.value = list
        _navigateToDetail.value = null
    }


}