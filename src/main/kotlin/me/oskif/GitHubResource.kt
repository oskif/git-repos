package me.oskif

import io.smallrye.mutiny.Uni
import jakarta.inject.Inject
import jakarta.ws.rs.*
import me.oskif.client.GitHubClient
import me.oskif.model.RepoWithBranches
import org.eclipse.microprofile.rest.client.inject.RestClient
import org.jboss.resteasy.reactive.RestResponse

@Path("/repos")
class GitHubResource {

    @Inject
    @RestClient
    lateinit var gitHubClient: GitHubClient

    @GET
    @Path("/{username}")
    fun getRepositories(@PathParam("username") username: String): Uni<RestResponse<List<RepoWithBranches>>> {
        return gitHubClient.getUserRepositories(username)
            .onItem().transformToUni { repos ->
                val nonForkRepos = repos.filter { !it.fork }
                val branchesUnis = nonForkRepos.map { repo ->
                    gitHubClient.getRepositoryBranches(repo.owner.login, repo.name)
                        .map { branches -> RepoWithBranches(repo.name, repo.owner.login, branches) }
                }
                Uni.join().all(branchesUnis).andCollectFailures()
            }
            .map { repoList -> RestResponse.ok(repoList) }
            .onFailure().transform { throwable -> throw NotFoundException(throwable.message) }
    }
}