package com.sirketismi.noteapp.feature.notes

import android.os.Bundle
import android.util.LayoutDirection
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.sirketismi.noteapp.R
import com.sirketismi.noteapp.databinding.FragmentNotesBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NotesFragment : Fragment() {

    lateinit var noteListAdapter : NoteListAdapter

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

        binding.btnSave.setOnClickListener {
            val action = NotesFragmentDirections.actionNotesToNewnote()
            findNavController().navigate(action)
        }

        prepareRecyclerView()
        observeLiveData()

        return binding.root
    }

    private fun observeLiveData() {
        viewModel.getAllData().observe(viewLifecycleOwner) {
            noteListAdapter.setList(it)
        }
    }

    private fun prepareRecyclerView() {
        context?.let {
            val layoutManager = LinearLayoutManager(it, LinearLayoutManager.VERTICAL, false)
            binding.recylerView.layoutManager = layoutManager
        }

        noteListAdapter = NoteListAdapter()
        binding.recylerView.adapter = noteListAdapter
    }
}