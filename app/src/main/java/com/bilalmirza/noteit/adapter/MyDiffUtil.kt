package com.bilalmirza.noteit.adapter

import androidx.recyclerview.widget.DiffUtil
import com.bilalmirza.noteit.model.Note

class MyDiffUtil(
    private val oldList: List<Note>,
    private val newList: List<Note>
): DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return when {
            oldList[oldItemPosition].id != newList[newItemPosition].id -> {
                return false
            }
            oldList[oldItemPosition].title != newList[newItemPosition].title -> {
                return false
            }
            oldList[oldItemPosition].content != newList[newItemPosition].content -> {
                return false
            }
            else -> true
        }
    }
}