package me.oskif

import io.quarkus.test.junit.QuarkusTest
import io.restassured.RestAssured.given
import io.restassured.common.mapper.TypeRef
import io.smallrye.common.constraint.Assert.assertNotNull
import io.smallrye.common.constraint.Assert.assertTrue
import me.oskif.model.RepoWithBranches
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

@QuarkusTest
class GitHubResourceTest {

    @Test
    fun testGetRepositoriesEndpoint() {
        val user = "octocat"

        val response = given()
          .`when`().get("/repos/$user")
          .then()
            .statusCode(200)
            .extract()
            .`as`(object : TypeRef<List<RepoWithBranches>>(){})

        assertNotNull(response)
        assertTrue(response.isNotEmpty())
        assertTrue(response.size == 6)
        assertEquals("octocat", response[0].ownerLogin)
        assertTrue(response.first().repositoryName == "git-consortium")
    }
}