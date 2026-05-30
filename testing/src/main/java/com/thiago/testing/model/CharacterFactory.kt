package com.thiago.testing.model

import com.thiago.core.domain.model.Character

class CharacterFactory {

    fun create(hero : Hero) = when (hero){
        Hero.ThreeDMan ->Character(
            "3-D Man",
            "http://i.annihil.us/u/prod/marvel/i/mg/c/e0/535fecbbb9784"
        )
        Hero.ABomb ->   Character(
            "A-Bomb (HAS)",
            "http://i.annihil.us/u/prod/marvel/i/mg/3/20/5232158de5b16"
        )
    }


    sealed class  Hero {
        object  ThreeDMan : Hero()
        object  ABomb : Hero()
    }
}