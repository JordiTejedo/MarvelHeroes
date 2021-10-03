package com.jorditejedo.marvelheroes.utils

import com.jorditejedo.marvelheroes.BuildConfig
import java.math.BigInteger
import java.security.MessageDigest

object Helpers {
    fun getHashForMarvelApi(timestamp: Long) : String {
        val input = timestamp.toString() + BuildConfig.MARVEL_API_PRIVATE_KEY + BuildConfig.MARVEL_API_PUBLIC_KEY

        val md = MessageDigest.getInstance("MD5")
        return BigInteger(1, md.digest(input.toByteArray())).toString(16).padStart(32, '0')
    }
}