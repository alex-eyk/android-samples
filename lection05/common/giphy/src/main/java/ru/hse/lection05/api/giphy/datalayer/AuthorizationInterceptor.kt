package ru.hse.lection05.api.giphy.datalayer

import okhttp3.Interceptor
import okhttp3.Response

class AuthorizationInterceptor(protected val key: String): Interceptor {
    companion object {
        const val API_KEY = "api_key"

        const val HEADER_HOST = "X-RapidAPI-Host"
        const val HEADER_KEY = "X-RapidAPI-Key"

        const val VALUE_HOST = "giphy.p.rapidapi.com"
        const val VALUE_KEY = "b6c34f2048mshcf5f5180af0248dp13843cjsna8c6d25ebe2d"
    }


    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()

        val url = original.url()
            .newBuilder()
            .addQueryParameter(API_KEY, key)
            .build()


        val request = original.newBuilder()
            .addHeader(HEADER_HOST, VALUE_HOST)
            .addHeader(HEADER_KEY, VALUE_KEY)
            .url(url)
            .build()

        return chain.proceed(request)
    }
}