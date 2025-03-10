package me.oskif.client

import io.smallrye.mutiny.Uni
import jakarta.ws.rs.GET
import jakarta.ws.rs.Path
import jakarta.ws.rs.PathParam
import jakarta.ws.rs.Produces
import jakarta.ws.rs.core.MediaType
import me.oskif.model.Branch
import me.oskif.model.Repository
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient


@RegisterRestClient(baseUri = "https://api.github.com")
interface GitHubClient {

    @GET
    @Path("/users/{username}/repos")
    @Produces(MediaType.APPLICATION_JSON)
    fun getUserRepositories(@PathParam("username") username: String): Uni<List<Repository>>

    @GET
    @Path("/repos/{owner}/{repo}/branches")
    @Produces(MediaType.APPLICATION_JSON)
    fun getRepositoryBranches(
        @PathParam("owner") owner: String,
        @PathParam("repo") repo: String
    ): Uni<List<Branch>>

}