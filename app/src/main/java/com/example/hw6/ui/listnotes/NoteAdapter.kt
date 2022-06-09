package com.example.hw6.ui.listnotes


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.hw6.databinding.ItemNoteBinding
import com.example.hw6.model.Note
import java.util.*

class NoteAdapter : ListAdapter<Note, NoteAdapter.NoteViewHolder>(NoteDiffCallback) {

    private var onItemLongClick: ((Note) -> Unit)? = null

    fun setOnItemLongClick(callback: (Note) -> Unit) {
        onItemLongClick = callback
    }

    inner class NoteViewHolder(
        private val binding: ItemNoteBinding
    ) : RecyclerView.ViewHolder(binding.root), View.OnLongClickListener {

        fun bind(item: Note) {
            binding.cardText.text = item.text
            binding.root.setOnLongClickListener {
                onItemLongClick?.invoke(item)
                true
            }
        }

        override fun onLongClick(p0: View?): Boolean {
            return true
        }
    }

    object NoteDiffCallback : DiffUtil.ItemCallback<Note>() {
        override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        return NoteViewHolder(
            ItemNoteBinding.inflate(inflater, parent, false)
        )
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}