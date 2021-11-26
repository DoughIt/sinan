package com.doughit.sinan.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * @Description 
 * 
 * @Copyright DoughIt Studio - Powered By DoughIt
 * @author Jerry Zhang <https://github.com/doughit>
 * @date 2021-11-23 12:36
 */
object ServiceCreator {
    private const val BASE_URL = "https://api.doughit.cn"

    private val mRetrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun <T> create(serviceClass: Class<T>): T = mRetrofit.create(serviceClass)

    inline fun <reified T> create(): T = create(T::class.java)
}