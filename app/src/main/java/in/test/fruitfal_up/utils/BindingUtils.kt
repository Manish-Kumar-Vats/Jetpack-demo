package `in`.test.fruitfal_up.ui

import `in`.test.fruitfal_up.response.CommitResponse
import android.widget.TextView
import androidx.databinding.BindingAdapter

@BindingAdapter("dateFormatted")
fun TextView.setDateFormatted(item: CommitResponse) {
    item.let {
        text = formattedDate(item.commit?.author?.authorName!!)
    }
}