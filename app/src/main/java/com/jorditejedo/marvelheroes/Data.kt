package com.jorditejedo.marvelheroes

import com.jorditejedo.marvelheroes.db.entity.Character

data class Data (
    val offset : Int,
    val limit : Int,
    val total : Int,
    val count : Int,
    val results : List<Character>
)