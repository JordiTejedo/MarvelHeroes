package com.jorditejedo.marvelheroes.ui

sealed class Destination(val route: String) {
    object ListScreen : Destination("listScreen")
    object DetailsScreen : Destination("detailsScreen/{characterId}")
}