package me.oskif.model

import kotlinx.serialization.Serializable

@Serializable
data class Branch(
    val name: String,
    val commit: Commit,
)