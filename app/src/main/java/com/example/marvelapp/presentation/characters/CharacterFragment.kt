package com.example.marvelapp.presentation.characters

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.marvelapp.R
import com.example.marvelapp.databinding.FragmentCharacterBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint

class CharacterFragment : Fragment(){

    private var _binding : FragmentCharacterBinding? = null
    private  val binding : FragmentCharacterBinding get()  = _binding!!

    private val characterAdapter = CharacterAdapter()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentCharacterBinding.inflate(
        inflater,
        container,
        false
    ).apply {
        _binding = this
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initCharactersAdapter()
    }

    private fun initCharactersAdapter(){
       with(binding.recyclerCharacter){
           setHasFixedSize(true)
           adapter =characterAdapter
       }
    }

}