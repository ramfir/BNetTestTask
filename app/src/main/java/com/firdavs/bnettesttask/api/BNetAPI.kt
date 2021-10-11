package com.firdavs.bnettesttask.api

import com.firdavs.bnettesttask.models.AddEntry
import com.firdavs.bnettesttask.models.Entries
import com.firdavs.bnettesttask.models.Session
import retrofit2.http.*

interface BNetAPI {

    @FormUrlEncoded
    @POST("testAPI/")
    suspend fun newSession(@Field("a") a: String): Session

    @FormUrlEncoded
    @POST("testAPI/")
    suspend fun getEntries(
        @Field("a") a: String,
        @Field("session") session: String
    ): Entries

    @FormUrlEncoded
    @POST("testAPI/")
    suspend fun addEntry(
        @Field("a") a: String,
        @Field("session") session: String,
        @Field("body") body: String
    ): AddEntry
}