package com.jorditejedo.marvelheroes.ui

import androidx.lifecycle.ViewModel
import com.jorditejedo.marvelheroes.db.entity.Character
import com.jorditejedo.marvelheroes.repository.CharacterRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(private val characterRepository: CharacterRepository) : ViewModel() {

    fun getCharacter(id: Int) : Character {
        return characterRepository.getCharacterFromLocalDB(id)
    }
}