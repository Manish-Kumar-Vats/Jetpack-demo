package `in`.test.fruitfal_up.adapter

import `in`.test.fruitfal_up.databinding.LayoutCommitItemBinding
import `in`.test.fruitfal_up.response.CommitResponse
import `in`.test.fruitfal_up.ui.formattedDate
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import java.util.*

class HomeListAdapter(val clickListener: CommitListener) :
    ListAdapter<CommitResponse, HomeListAdapter.ViewHolder>(CommitDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, position, clickListener)
    }


    class ViewHolder private constructor(val binding: LayoutCommitItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = LayoutCommitItemBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }


        fun bind(item: CommitResponse, position: Int, clickListener: CommitListener) {

            binding.clickListener = clickListener
            binding.commit = item
            binding.executePendingBindings()
            binding.commitAuthor.text = item.commit?.author?.authorName
            binding.commitDate.text = formattedDate(item.commit?.author?.commitDate!!)
            binding.commitMessage.text = item.commit.commitMessage
            binding.commitSha.text = item.sha

        }
    }

    companion object {
    }

}

class CommitDiffCallback : DiffUtil.ItemCallback<CommitResponse>() {
    override fun areItemsTheSame(oldItem: CommitResponse, newItem: CommitResponse): Boolean {
        return oldItem.sha == newItem.sha
    }

    override fun areContentsTheSame(oldItem: CommitResponse, newItem: CommitResponse): Boolean {
        return oldItem == newItem
    }

}

class CommitListener(val clickListener: (commitSha: String) -> Unit) {
    fun onClick(commit: CommitResponse) = clickListener(commit.sha)
}