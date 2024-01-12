package com.sirketismi.noteapp.feature.newnote

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.sirketismi.noteapp.databinding.FragmentAddNoteBinding
import com.sirketismi.noteapp.util.showDatePicker
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddNewNoteFragment : BottomSheetDialogFragment() {

    lateinit var binding : FragmentAddNoteBinding
    val viewModel: AddNewNoteViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentAddNoteBinding.inflate(inflater)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        viewModel.onSaveCompleted.observe(viewLifecycleOwner) {
            if(it) {
                findNavController().navigateUp()
            }
        }

        viewModel.onShowDatePickerTapped.observe(viewLifecycleOwner) {
            if(it) {
                showDatePicker() {
                    viewModel.userSelectDate(it)
                }
            }
        }

        dialog?.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)

        return binding.root
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        if (dialog is BottomSheetDialog) {
            dialog.behavior.skipCollapsed = true
            dialog.behavior.state = BottomSheetBehavior.STATE_EXPANDED
        }
        return dialog
    }
}