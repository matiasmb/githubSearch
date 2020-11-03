package com.matiasmb.githubsearch.data.networking

import com.matiasmb.githubsearch.data.model.GithubRepo
import kotlinx.coroutines.Deferred
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface ItemsApiClient {

    /**
     * List public repositories for the specified username.
     *
     * @param username - the username used to search repos.
     * @return a list of public repos.
     */
    @Headers("User-Agent: GitHubMVP-App")
    @GET("users/{username}/repos")
    suspend fun searchReposByUsername(@Path("username") username: String): List<GithubRepo>
}