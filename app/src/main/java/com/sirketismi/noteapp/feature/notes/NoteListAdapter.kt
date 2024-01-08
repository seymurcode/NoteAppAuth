package com.sirketismi.noteapp.feature.notes

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sirketismi.noteapp.databinding.ListItemNoteBinding
import com.sirketismi.noteapp.model.NoteEntity

class NoteListAdapter() : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var noteList = listOf<NoteEntity>()
    fun setList(list : List<NoteEntity>) {
        this.noteList = list
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return noteList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        var viewHolder : RecyclerView.ViewHolder? = null
        var binding = ListItemNoteBinding.inflate(LayoutInflater.from(parent.context))
        viewHolder = NoteViewHolder(binding)

        return viewHolder
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val viewHolder = holder as NoteViewHolder
        viewHolder.bind(noteList.get(position))
    }

}

class NoteViewHolder(val binding : ListItemNoteBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(note : NoteEntity) {
        binding.txtNote.text = note.title
        binding.txtNoteDetail.text = note.detail
    }
}