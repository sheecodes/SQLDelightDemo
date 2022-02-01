package com.sheecodes.sqldelightdemo

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sheecodes.sqldelightdemo.databinding.NoteRowBinding
import demo.notesdb.NotesEntity

class NotesAdapter(private val onClickListener: OnClickListener) : ListAdapter<NotesEntity, NotesAdapter.MyViewHolder>(PRODUCTS_COMPARATOR) {

    companion object {
        private val PRODUCTS_COMPARATOR = object : DiffUtil.ItemCallback<NotesEntity>() {
            override fun areItemsTheSame(old: NotesEntity, aNew: NotesEntity): Boolean {
                return old.title == aNew.title
            }

            override fun areContentsTheSame(old: NotesEntity, aNew: NotesEntity): Boolean {
                return old == aNew
            }
        }
    }

    inner class MyViewHolder(private val binding: NoteRowBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")

        val deleteButton = binding.imageViewDelete

        fun bind(note: NotesEntity?) {
            binding.textViewTitle.text = note?.title
            binding.textViewDescription.text = note?.description
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            NoteRowBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val note = getItem(position)
        holder.bind(note)

        holder.deleteButton.setOnClickListener {
            onClickListener.onClick(note)
        }
    }

    class OnClickListener(val clickListener: (note: NotesEntity) -> Unit) {
        fun onClick(note: NotesEntity) = clickListener(note)
    }
}