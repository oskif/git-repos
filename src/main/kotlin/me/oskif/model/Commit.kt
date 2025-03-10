package me.oskif.model

import kotlinx.serialization.Serializable

@Serializable
data class Commit (
    val sha: String
)