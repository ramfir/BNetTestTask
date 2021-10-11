package com.firdavs.bnettesttask.models

data class Session(
    var status: Int,
    var data: SessionId
)

data class SessionId(
    val session: String
)