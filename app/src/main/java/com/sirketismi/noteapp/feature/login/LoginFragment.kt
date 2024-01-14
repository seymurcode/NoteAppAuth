package com.sirketismi.noteapp.feature.login

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
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

        auth = Firebase.auth

        binding.loginButtonGoogle.setOnClickListener {
            loginGoogle()
        }
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


    private lateinit var auth: FirebaseAuth
    private lateinit var signInRequest: BeginSignInRequest

    private val REQ_ONE_TAP = 2  // Can be any integer unique to the Activity
    private lateinit var mGoogleSignInClient : GoogleSignInClient
    fun loginGoogle() {

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestServerAuthCode(getString(R.string.google_client_id))
            .requestIdToken(getString(R.string.google_client_id))
            .requestEmail().build()

        mGoogleSignInClient = GoogleSignIn.getClient(requireContext(), gso)

        signInRequest = BeginSignInRequest.builder()
            .setGoogleIdTokenRequestOptions(
                BeginSignInRequest.GoogleIdTokenRequestOptions.builder()
                    .setSupported(true)
                    // Your server's client ID, not your Android client ID.
                    .setServerClientId(getString(R.string.google_client_id))
                    // Only show accounts previously used to sign in.
                    .setFilterByAuthorizedAccounts(true)
                    .build())
            .build()

        val signInIntent = mGoogleSignInClient.signInIntent
        googleLauncher.launch(signInIntent)
    }

    private val googleLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: androidx.activity.result.ActivityResult ->

        result.data?.let {
            var result = Auth.GoogleSignInApi.getSignInResultFromIntent(it)
            if(result?.isSuccess == true) {
                val acct = result.signInAccount
                val authCode = acct!!.serverAuthCode
                val idToken = acct!!.idToken

                val firebaseCredential = GoogleAuthProvider.getCredential(idToken, null)
                auth.signInWithCredential(firebaseCredential)
                    .addOnCompleteListener(requireActivity()) { task ->
                        if (task.isSuccessful) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("TAG", "signInWithCredential:success")
                            val user = Firebase.auth.currentUser
                            //updateUI(user)
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("TAG", "signInWithCredential:failure", task.exception)
                            //updateUI(null)
                        }
                    }
            }
        }

    }
}