package com.sirketismi.noteapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import android.widget.Button
import android.widget.MediaController
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.google.firebase.Firebase
import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.google.firebase.firestore.firestore
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        navController = navHostFragment.navController

        /*navController.graph.setStartDestination(R.id.loginFragment)

        navController.addOnDestinationChangedListener { prm, destination, _ ->
            if(destination.id == R.id.splashFragment) {

            }
        }*/

        /*val crashButton = Button(this)
        crashButton.text = "Test Crash"

        FirebaseCrashlytics.getInstance().log("Main Activity onCreate")

        crashButton.setOnClickListener {
            throw RuntimeException("Test Crash") // Force a crash
        }

        addContentView(crashButton, ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT))
*/
        val db = Firebase.firestore

/*var note = hashMapOf(
   "name-1" to "123123",
   "title-1" to "21321",
   "detail-1" to "dasdasd23"
   )

val noteCollection = db.collection("NoteApp")
noteCollection.addSnapshotListener { value, error ->

}



   noteCollection.add(note)
   .addOnSuccessListener {

   }
   .addOnFailureListener {

   }

noteCollection
   .get()
   .addOnSuccessListener {

   }
   .addOnFailureListener {

   }*/

}

    override fun onResume() {
        super.onResume()
        FirebaseCrashlytics.getInstance().log("Main Activity onResume")

    }
}