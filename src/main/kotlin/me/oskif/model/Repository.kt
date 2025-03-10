package me.oskif.model

import kotlinx.serialization.Serializable

@Serializable
data class Repository(
    val name: String,
    val owner: Owner,
    val fork: Boolean
)