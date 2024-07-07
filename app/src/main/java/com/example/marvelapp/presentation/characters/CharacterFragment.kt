package com.example.marvelapp.presentation.characters

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.paging.LoadState
import com.example.marvelapp.R
import com.example.marvelapp.databinding.FragmentCharacterBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

//https://developer.android.com/topic/libraries/architecture/paging/load-state?hl=pt-br
@AndroidEntryPoint
class CharacterFragment : Fragment(){

    private var _binding : FragmentCharacterBinding? = null
    private  val binding : FragmentCharacterBinding get()  = _binding!!

    private val viewModel : CharactersViewModel by viewModels()

    private lateinit var characterAdapter : CharacterAdapter
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
        observeInitialLoadingState()
        lifecycleScope.launch {
            //Vai fazer o stop do flow quando estiver em background , quando clicar no app novamente vai coletar o fluxo
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.charactersPagingData("").collect{pagingData->
                    characterAdapter.submitData(pagingData)

                }
            }

        }

    }

    private fun initCharactersAdapter(){
        characterAdapter = CharacterAdapter()
       with(binding.recyclerCharacter){
           scrollToPosition(0)
           setHasFixedSize(true)
           adapter =characterAdapter.withLoadStateFooter(
               footer = CharactersLoadStateAdapter(
                   characterAdapter::retry
               )
           )
       }
    }

    private fun observeInitialLoadingState(){
        lifecycleScope.launch {
        characterAdapter.loadStateFlow.collectLatest {loadState->
           binding.flipperCharacter.displayedChild =  when(loadState.refresh){
                is LoadState.Loading ->{
                    setShimmerVisibility(true)
                    FLIPPER_CHILD_LOADING
                }
               is LoadState.NotLoading -> {
                   setShimmerVisibility(false)
                   FLIPPER_CHILD_CHARACTERS

               }
               is LoadState.Error -> {
                   setShimmerVisibility(false)
                   binding.includeViewCharactersErrorState.buttonRetry.setOnClickListener {
                       characterAdapter.refresh()
                   }
                   FLIPPER_CHILD_ERROR
               }

            }


        }
        }
    }
private fun setShimmerVisibility(visibility : Boolean){
    binding.includeCharactersLoadingState.shimmerCharacters.run {
        isVisible = visibility
        if(visibility){
            startShimmer()
        }else {

        stopShimmer()}
    }
}
    companion object {
        private  const val FLIPPER_CHILD_LOADING = 0
        private  const val FLIPPER_CHILD_CHARACTERS = 1
        private  const val FLIPPER_CHILD_ERROR = 2
    }
}