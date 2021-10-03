package com.jorditejedo.marvelheroes.ui

import androidx.lifecycle.ViewModel
import com.jorditejedo.marvelheroes.repository.CharacterRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(private val characterRepository: CharacterRepository) : ViewModel() {
    val characters = characterRepository.getObservableCharactersFromLocalDB()
}