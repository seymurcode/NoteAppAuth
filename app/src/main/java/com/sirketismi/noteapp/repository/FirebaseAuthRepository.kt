package com.sirketismi.noteapp.repository

import androidx.navigation.fragment.findNavController
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.auth
import com.google.firebase.firestore.Filter
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.sirketismi.noteapp.model.NoteEntity
import com.sirketismi.noteapp.util.FirebaseMessageHandler
import com.sirketismi.noteapp.util.showMessage
import java.lang.Exception
import javax.inject.Inject

interface FirebaseAuthRepositoryInterface {
    fun register(
        email: String,
        password: String,
        success: () -> Unit,
        error: (exception: Exception) -> Unit
    )

    fun login(
        email: String,
        password: String,
        success: () -> Unit,
        error: (exception: Exception) -> Unit
    )
}
class FirebaseAuthRepository @Inject constructor(val firebaseAuth : FirebaseAuth) : FirebaseAuthRepositoryInterface {
    override fun register(email : String, password : String,  success: () -> Unit, error: (exception : Exception) -> Unit) {
        firebaseAuth.createUserWithEmailAndPassword(
            email,
            password)
            .addOnSuccessListener {
                success()
            }.addOnFailureListener {
                error(it)
            }
    }

    override fun login(
        email: String,
        password: String,
        success: () -> Unit,
        error: (exception: Exception) -> Unit
    ) {
        firebaseAuth.signInWithEmailAndPassword(
            email,
            password)
            .addOnSuccessListener {
                success()
            }.addOnFailureListener {
                error(it)
            }
    }


}