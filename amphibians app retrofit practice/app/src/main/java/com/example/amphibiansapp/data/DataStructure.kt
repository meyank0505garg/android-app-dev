package com.example.amphibiansapp.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class DataStructure(
    val name : String,
    val type : String,
    val description : String,
    @SerialName(value = "img_src")
    val imgSrc : String
)


