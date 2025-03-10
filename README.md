# git-repos

This application is a Quarkus-based REST API that retrieves a list 
of non-fork GitHub repositories for a given user, including information about their branches.

## Prerequisites

* Java 21+
* Maven 3.8+
* Kotlin 2.0.21
* Quarkus 3.19.2

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:

```shell script
./mvnw quarkus:dev
```

## Endpoint

`GET /repository/{username}`

### Input
* username - GitHub username

### Output
* Returns a list of repositories (excluding forks) with their branches.
* If the user does not exist, it returns a 404 Not Found response.

### Request:
`GET /repositories/nonexistentuser`

### Response(`OK`)

```json
[
  {
    "repositoryName": "git-consortium",
    "ownerLogin": "octocat",
    "branches": [
      {
        "name": "master",
        "commit": {
          "sha": "b33a9c7c02ad93f621fa38f0e9fc9e867e12fa0e"
        }
      }
    ]
  },
  {
    "repositoryName": "hello-worId",
    "ownerLogin": "octocat",
    "branches": [
      {
        "name": "master",
        "commit": {
          "sha": "7e068727fdb347b685b658d2981f8c85f7bf0585"
        }
      }
    ]
  }
]
```

### Response(`Not found`)

```json
{
    "status": 404,
    "message": "Received: 'rate limit exceeded, status code 403' when invoking REST Client method: 'me.oskif.client.GitHubClient#getUserRepositories'"
}
```