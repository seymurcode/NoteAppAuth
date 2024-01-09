package com.sirketismi.noteapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sirketismi.noteapp.databinding.ListItemTagsBinding
import com.sirketismi.noteapp.model.NoteEntity
import com.sirketismi.noteapp.util.format
import java.util.Date

class TagListAdapter() : RecyclerView.Adapter<RecyclerView.ViewHolder>() {



    var newList = mutableListOf<String>()
    var noteList = listOf<NoteEntity>()

    fun setList(list : List<NoteEntity>) {
        this.noteList = list

        newList.clear()
        noteList.forEach {
            if(!newList.contains(it.tag))
                newList.add(it.tag)
        }

        notifyDataSetChanged()
    }


    override fun getItemCount(): Int {
        return newList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        var viewHolder : RecyclerView.ViewHolder? = null

        val binding = ListItemTagsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        viewHolder = TagViewHolder(binding)
        return viewHolder
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val viewHolder = holder as TagViewHolder
        val item = newList.get(position)
        viewHolder.bind(item)
    }
}


class TagViewHolder(val binding : ListItemTagsBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(title : String) {
        binding.txtNote.text = title
    }
}