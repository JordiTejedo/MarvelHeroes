package com.jorditejedo.marvelheroes.client.type

import retrofit2.Response
import timber.log.Timber

sealed class ApiResponse<T> {
    companion object {
        fun <T> create(response: Response<T>): ApiResponse<T> {
            return if (response.isSuccessful) {
                val body = response.body()
                if (body == null || response.code() == 204) {
                    ApiEmptyResponse()
                } else {
                    ApiSuccessResponse(body)
                }
            } else {
                Timber.d("Error -> $response")

                ApiErrorResponse(ResponseType.classify(response.code()))
            }
        }

        fun <T> create(error: Throwable): ApiErrorResponse<T> {
            return ApiErrorResponse(ResponseType.classify(error))
        }
    }
}

/**
 * separate class for HTTP 204 resposes so that we can make ApiSuccessResponse's body non-null.
 */
class ApiEmptyResponse<T> : ApiResponse<T>()

data class ApiSuccessResponse<T>(val body: T) : ApiResponse<T>()
data class ApiErrorResponse<T>(val errorType: ResponseType) : ApiResponse<T>()