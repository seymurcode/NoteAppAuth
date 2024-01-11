package com.sirketismi.noteapp.feature.login

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.sirketismi.noteapp.R
import com.sirketismi.noteapp.repository.FirebaseAuthRepository
import com.sirketismi.noteapp.repository.FirebaseRepository
import com.sirketismi.noteapp.repository.NotesRepository
import com.sirketismi.noteapp.util.showMessage
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    val repository: NotesRepository,
    val firebaseRepository: FirebaseRepository, val firebaseAuthRepository: FirebaseAuthRepository,
    @ApplicationContext val appContext: Context
) : ViewModel() {


    var email = MutableLiveData<String>()
    var password = MutableLiveData<String>()


    var onSuccessLogin = MutableLiveData<Boolean>(false)
    var onErrorLogin = MutableLiveData<Boolean>(false)
    var onRegisterClick = MutableLiveData<Boolean>(false)

    private fun isValid() : Boolean {
        return !(email.value.isNullOrEmpty() || password.value.isNullOrEmpty())
    }
    fun onLoginButtonClick() {
        println("onLoginButtonClick")

        if(isValid()) {

            firebaseAuthRepository.login(email.value.toString(),
                password.value.toString(),{
                    onSuccessLogin.postValue(true)
                },
                {
                    onErrorLogin.postValue(true)
                })
        }
        else{
            println("bosduuuuu")
        }
    }

    fun onRegisterButtonClick(){
        println("onRegisterButtonClick")

        onRegisterClick.postValue(true)
    }
}