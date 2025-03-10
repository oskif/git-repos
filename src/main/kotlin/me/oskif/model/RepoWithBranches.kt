package me.oskif.model

import kotlinx.serialization.Serializable

@Serializable
data class RepoWithBranches(
    val repositoryName: String,
    val ownerLogin: String,
    val branches: List<Branch>
)