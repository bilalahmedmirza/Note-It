package com.bilalmirza.noteit.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bilalmirza.noteit.databinding.NoteItemBinding
import com.bilalmirza.noteit.model.Note
import com.bilalmirza.noteit.ui.fragments.list.ListFragmentDirections

class ListAdapter: RecyclerView.Adapter<ListAdapter.MyViewHolder>() {
    private var noteList = emptyList<Note>()

    class MyViewHolder(val binding: NoteItemBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(NoteItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = noteList[position]
        holder.binding.title.text = currentItem.title
        holder.binding.content.text = currentItem.content

        holder.binding.noteRow.setOnClickListener {
            holder.itemView.findNavController().navigate(ListFragmentDirections.actionListFragmentToUpdateNoteFragment(currentItem))
        }
    }

    override fun getItemCount(): Int {
        return noteList.size
    }

    fun updateData(newNoteList: List<Note>) {
        val diffUtil = MyDiffUtil(noteList, newNoteList)
        val diffResults =DiffUtil.calculateDiff(diffUtil)
        noteList = newNoteList
        diffResults.dispatchUpdatesTo(this)
    }
}