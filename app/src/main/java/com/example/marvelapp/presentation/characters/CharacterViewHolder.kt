package com.example.marvelapp.presentation.characters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.marvelapp.R
import com.example.marvelapp.databinding.ItemCaracterBinding
import com.thiago.core.domain.model.Character

class CharacterViewHolder(
    itemCharacterBinding : ItemCaracterBinding
) : RecyclerView.ViewHolder(itemCharacterBinding.root){

    private val textName = itemCharacterBinding.textName
    private val  imageCharacter= itemCharacterBinding.imageCharacter

    fun bind(character : Character){
        textName.text = character.name
        Glide.with(itemView)
            .load(character.imageUrl)
            .fallback(R.drawable.ic_img_loading_error)
            .into(imageCharacter)
    }

    companion object{
        fun create(parent : ViewGroup):CharacterViewHolder{
            val inflater = LayoutInflater.from(parent.context)
            val itemBinding = ItemCaracterBinding.inflate(inflater,parent,false )
            return  CharacterViewHolder(itemBinding)
        }
    }
}