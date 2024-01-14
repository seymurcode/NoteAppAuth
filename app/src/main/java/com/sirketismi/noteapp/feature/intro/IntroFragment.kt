package com.sirketismi.noteapp.feature.intro

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.sirketismi.noteapp.databinding.FragmentIntroBinding
import com.sirketismi.noteapp.feature.splash.SplashFragmentDirections

// TODO: Rename parameter arguments, choose names that match
/**
 * A simple [Fragment] subclass.
 * Use the [IntroFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class IntroFragment : Fragment() {

    lateinit var binding: FragmentIntroBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentIntroBinding.inflate(inflater)
        binding.getStartedButton.setOnClickListener {
            val action = IntroFragmentDirections.actionIntroFragmentToSplash()
            findNavController().navigate(action)
        }
        // Inflate the layout for this fragment
        return binding.root
    }
}