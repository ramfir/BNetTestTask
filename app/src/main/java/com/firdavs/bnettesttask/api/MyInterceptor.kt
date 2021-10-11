package com.firdavs.bnettesttask.api

import com.firdavs.bnettesttask.utils.Constants
import okhttp3.Interceptor
import okhttp3.Response

class MyInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
            .newBuilder()
            .addHeader("token", Constants.TOKEN)
            .build()

        return chain.proceed(request)
    }

}