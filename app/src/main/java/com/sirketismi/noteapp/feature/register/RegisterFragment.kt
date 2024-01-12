package com.sirketismi.noteapp.feature.register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.firebase.Firebase
import com.google.firebase.FirebaseError
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.auth
import com.sirketismi.noteapp.R
import com.sirketismi.noteapp.databinding.FragmentRegisterBinding
import com.sirketismi.noteapp.util.FirebaseMessageHandler
import com.sirketismi.noteapp.util.showMessage
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterFragment : Fragment() {
    lateinit var binding : FragmentRegisterBinding
    val viewModel :  RegisterViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRegisterBinding.inflate(inflater)
        binding.viewModel=viewModel

        return binding.root
    }



    fun observeAll(){
        viewModel.onSuccessRegister.observe(viewLifecycleOwner) {
            if(it) {
                showMessage(getString(R.string.success), getString(R.string.success_register))
                findNavController().popBackStack()
            }
        }
        viewModel.onErrorRegister.observe(viewLifecycleOwner) {
            (it as? FirebaseAuthException)?.errorCode?.let { errorCode->
                showMessage(getString(R.string.error), FirebaseMessageHandler.getLocalizedMessage(errorCode))
            }
        }
        viewModel.onErrorEnteredData.observe(viewLifecycleOwner) {
            showMessage(getString(R.string.error_login_title), it)
        }
    }
    fun removeAllObservers(){
        viewModel.onErrorRegister.removeObservers(this)
        viewModel.onSuccessRegister.postValue(false)
        viewModel.onErrorEnteredData.removeObservers(this)
    }

    override fun onResume() {
        super.onResume()
        observeAll()
    }

    override fun onPause() {
        super.onPause()
        removeAllObservers()
    }
}