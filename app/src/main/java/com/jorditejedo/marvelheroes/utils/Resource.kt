package com.jorditejedo.marvelheroes.utils

import com.jorditejedo.marvelheroes.utils.NetworkResourceStatus.*
import com.jorditejedo.marvelheroes.client.type.ResponseType

data class Resource<out T>(val status: NetworkResourceStatus, val data: T?, val responseType: ResponseType?) {
    companion object {
        fun <T> success(data: T?): Resource<T> {
            return Resource(SUCCESS, data, null)
        }

        fun <T> error(responseType: ResponseType, data: T?): Resource<T> {
            return Resource(ERROR, data, responseType)
        }

        fun <T> loading(data: T?): Resource<T> {
            return Resource(LOADING, data, null)
        }
    }
}