package `in`.test.fruitfal_up.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeListViewModel : ViewModel() {


    private var _commitList = MutableLiveData<List<String>>()

    val commitList = _commitList

    init {
        val list = ArrayList<String>()
        list.add("asfas")
        list.add("dvsvsd")
        list.add("dsvsdv")
        list.add("asfacvsbas")
        list.add("avsdvsdsfas")
        list.add("avssdsfas")
        list.add("vdsvsv")
        _commitList.value = list
    }


}