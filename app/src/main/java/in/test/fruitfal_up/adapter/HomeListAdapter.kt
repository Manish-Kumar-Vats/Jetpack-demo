package `in`.test.fruitfal_up.adapter

import `in`.test.fruitfal_up.R
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

class HomeListAdapter : RecyclerView.Adapter<HomeListAdapter.ViewHolder>() {

    var data = listOf<String>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemCount(): Int = data.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
        holder.bind(item, position)
    }


/*    fun clear() {
        mCommitList.clear()
        notifyDataSetChanged()
    }

    fun addItem(item: CommitsResponse) {
        mCommitList.add(item)
        notifyDataSetChanged()
    }

    fun removeItemAt(position: Int) {
        mCommitList.removeAt(position)
        notifyDataSetChanged()
    }

    fun addAll(addList: List<CommitsResponse>) {
        mCommitList.addAll(addList)
        notifyDataSetChanged()
    }*/

    class ViewHolder private constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val view = layoutInflater
                    .inflate(R.layout.layout_commit_item, parent, false)
                return ViewHolder(view)
            }
        }

        private val container: CardView = itemView.findViewById(R.id.container)
        private val commitAuthor: TextView = itemView.findViewById(R.id.commit_author)
        private val commitMessage: TextView = itemView.findViewById(R.id.commit_message)
        private val commitSha: TextView = itemView.findViewById(R.id.commit_sha)
        private val commitDate: TextView = itemView.findViewById(R.id.commit_date)


        fun bind(item: String, position: Int) {

            commitAuthor.text = item

            /*        commitAuthor.text = commitDetail
            val commitData: CommitModel? = commitDetail.commitData
                val authorData: AuthorModel? = commitDetail.commitData?.author

                commitAuthor.text = authorData?.authorName
                commitMessage.text = commitData?.commitMessage
                commitSha.text = commitDetail.sha
                commitDate.text = Tools().formattedDate(authorData?.commitDate)

    //            container.setOnClickListener { listener.onClick(commitDetail.url) }
                container.setOnClickListener { listener.onClick(commitDetail.sha) }*/
        }
    }

    companion object {
    }

}
