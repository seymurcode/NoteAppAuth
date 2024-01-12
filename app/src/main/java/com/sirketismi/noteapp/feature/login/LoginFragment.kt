package com.sirketismi.noteapp.feature.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.sirketismi.noteapp.R
import com.sirketismi.noteapp.databinding.FragmentLoginBinding
import com.sirketismi.noteapp.util.showMessage
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {
    lateinit var binding : FragmentLoginBinding
    val viewModel : LoginViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater)
        binding.viewModel=viewModel

        return binding.root
    }

    fun openApp() {
        val action = LoginFragmentDirections.actionLoginFragmentToNotesFragment()
        findNavController().navigate(action)
    }

    fun openRegisterPage() {
        val action = LoginFragmentDirections.actionLoginFragmentToRegisterFragment()
        findNavController().navigate(action)
    }

    fun observeAll() {
        viewModel.onSuccessLogin.observe(viewLifecycleOwner) {
            if(it)
                openApp()
        }
        viewModel.onErrorLogin.observe(viewLifecycleOwner) {
            showMessage(getString(R.string.error_login_title), getString(R.string.error_login_detail))
        }
        viewModel.onErrorEnteredData.observe(viewLifecycleOwner) {
            showMessage(getString(R.string.error_login_title), it)
        }
        viewModel.onRegisterClick.observe(viewLifecycleOwner) {
            if(it)
                openRegisterPage()
        }
    }

    fun removeAllObservers(){

        viewModel.onErrorLogin.removeObservers(this)
        viewModel.onSuccessLogin.postValue(false)
        viewModel.onRegisterClick.postValue(false)
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