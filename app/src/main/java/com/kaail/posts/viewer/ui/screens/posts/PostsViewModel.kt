package com.kaail.posts.viewer.ui.screens.posts

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kaail.posts.viewer.data.PostRemoteModel
import com.kaail.posts.viewer.data.PostsService
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class PostsViewModel @Inject constructor(
    private val postsService: PostsService
) : ViewModel() {

    private val _state = MutableStateFlow(PostsScreenState())
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            getPosts()
        }
    }

    private suspend fun getPosts() {
        val posts = postsService.getAllPosts()
        _state.update { it.copy(postsList = posts) }
    }
}

data class PostsScreenState(
    val postsList: List<PostRemoteModel> = emptyList()
)
