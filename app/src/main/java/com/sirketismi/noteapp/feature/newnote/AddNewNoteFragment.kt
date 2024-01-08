package com.sirketismi.noteapp.feature.newnote

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.sirketismi.noteapp.databinding.FragmentAddNoteBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddNewNoteFragment : BottomSheetDialogFragment() {

    lateinit var binding : FragmentAddNoteBinding
    val viewModel: AddNewNoteViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentAddNoteBinding.inflate(inflater)
        binding.viewModel = viewModel

        viewModel.onSaveCompleted.observe(viewLifecycleOwner) {
            if(it) {
                findNavController().navigateUp()
            }
        }
        return binding.root
    }
}