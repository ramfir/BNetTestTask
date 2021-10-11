package com.firdavs.bnettesttask.models

data class AddEntry(
    var status: Int,
    var data: ID
)

data class ID(
    var id: String
)