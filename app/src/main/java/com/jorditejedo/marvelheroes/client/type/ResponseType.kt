package com.jorditejedo.marvelheroes.client.type

import java.net.SocketTimeoutException
import java.net.UnknownHostException

enum class ResponseType {
    SUCCESS,
    NO_INTERNET,
    BAD_REQUEST, // 400
    UNAUTHORIZED, //401
    FORBIDDEN, // 403
    NOT_FOUND, // 404
    METHOD_NOT_ALLOWED, //405
    CONFLICT, // 409
    PRECONDITION_FAILED, // 412
    EXPECTATION_FAILED, // 417
    NO_SERVICE, // 503
    TIME_OUT, // 504
    UNKNOWN_ERROR; // 500 / other Application / (de)serialization

    companion object {
        fun classify(t: Throwable?) : ResponseType {
            return when(t){
                is UnknownHostException -> NO_INTERNET
                is SocketTimeoutException -> TIME_OUT
                //is TimeoutException -> TIME_OUT
                //is IOException -> NO_INTERNET
                else -> UNKNOWN_ERROR
            }
        }

        fun classify(code: Int?) : ResponseType {
            return when(code) {
                200, 204 -> SUCCESS
                400 -> BAD_REQUEST
                401 -> UNAUTHORIZED
                403 -> FORBIDDEN
                404 -> NOT_FOUND
                405 -> METHOD_NOT_ALLOWED
                409 -> CONFLICT
                412 -> PRECONDITION_FAILED
                417 -> EXPECTATION_FAILED
                503 -> NO_SERVICE
                504 -> TIME_OUT
                else -> UNKNOWN_ERROR
            }
        }
    }
}