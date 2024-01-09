package com.sirketismi.noteapp.feature.notes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.flexbox.FlexWrap
import com.google.android.flexbox.FlexboxLayoutManager
import com.sirketismi.noteapp.adapters.NoteListAdapter
import com.sirketismi.noteapp.adapters.TagListAdapter
import com.sirketismi.noteapp.databinding.FragmentNotesBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NotesFragment : Fragment() {

    lateinit var noteListAdapter : NoteListAdapter
    lateinit var tagListAdapter : TagListAdapter

    lateinit var binding : FragmentNotesBinding
    val viewModel : NotesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNotesBinding.inflate(inflater)

        binding.lifecycleOwner=viewLifecycleOwner
        binding.btnSave.setOnClickListener {
            val action = NotesFragmentDirections.actionNotesToNewnote()
            findNavController().navigate(action)
        }
        prepareTagsRecyclerView()
        prepareRecyclerView()
        observeLiveData()

        return binding.root
    }

    private fun observeLiveData() {
        viewModel.getAllData().observe(viewLifecycleOwner) {
            noteListAdapter.setList(it)
            tagListAdapter.setList(it)
        }
    }

    private fun prepareRecyclerView() {
        context?.let {
            val layoutManager = LinearLayoutManager(it, LinearLayoutManager.VERTICAL, false)
            //val layoutManager = GridLayoutManager(it, 3)
            binding.recylerView.layoutManager = layoutManager
        }

        noteListAdapter = NoteListAdapter()
        binding.recylerView.adapter = noteListAdapter
    }
    private fun prepareTagsRecyclerView() {
        context?.let {
           // val layoutManager = LinearLayoutManager(it, LinearLayoutManager.VERTICAL, false)
            //val layoutManager = GridLayoutManager(it, 3)
            val layoutManager = FlexboxLayoutManager(context)
            layoutManager.flexWrap = FlexWrap.WRAP
            binding.recylerViewTags.layoutManager = layoutManager
        }

        tagListAdapter = TagListAdapter()
        binding.recylerViewTags.adapter = tagListAdapter
    }
}