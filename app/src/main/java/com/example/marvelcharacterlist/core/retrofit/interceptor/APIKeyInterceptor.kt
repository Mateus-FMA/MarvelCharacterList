package com.example.marvelcharacterlist.core.retrofit.interceptor

import okhttp3.Interceptor
import okhttp3.Response
import java.security.MessageDigest
import java.util.*

class APIKeyInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val originalUrl = originalRequest.url()

        val publicKey = "108842433ff78c7cc923ed313c671259"
        val privateKey = "98f4bdc9cb0732154d47b80586686fff522eada3"
        val ts = Random(Date().time).nextLong()

        // Get MD5 hash.
        val msgDigest = MessageDigest.getInstance("MD5")
        msgDigest.update("$ts$privateKey$publicKey".toByteArray())

        val buffer = msgDigest.digest()
        val hexString = StringBuilder()

        buffer.forEach {
            var h = Integer.toHexString(0xFF and it.toInt())
            while (h.length < 2)
                h = "0$h"

            hexString.append(h)
        }

        val hash = hexString.toString()

        // Update URL.
        val url = originalUrl.newBuilder()
            .addQueryParameter("ts", ts.toString())
            .addQueryParameter("apikey", publicKey)
            .addQueryParameter("hash", hash)
            .build()

        val request = originalRequest.newBuilder()
            .url(url)
            .build()

        return chain.proceed(request)
    }

}