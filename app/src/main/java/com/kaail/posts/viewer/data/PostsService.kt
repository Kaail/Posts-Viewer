package com.kaail.posts.viewer.data

import retrofit2.http.GET

interface PostsService {
    @GET("/posts")
    suspend fun getAllPosts(): List<PostRemoteModel>
}
