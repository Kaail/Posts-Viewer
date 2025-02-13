package com.kaail.posts.viewer.ui.screens.posts

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.kaail.posts.viewer.data.PostRemoteModel

@Composable
fun PostsScreenContainer(modifier: Modifier = Modifier) {
    val viewModel: PostsViewModel = hiltViewModel()
    val postsScreenState by viewModel.state.collectAsState()

    PostsScreen(state = postsScreenState, modifier)
}

@Composable
fun PostsScreen(state: PostsScreenState, modifier: Modifier = Modifier) {
    val lazyListState = rememberLazyListState()

    LazyColumn(
        modifier = Modifier
            .then(modifier)
            .fillMaxSize()
            .padding(16.dp),
        state = lazyListState,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(items = state.postsList, key = { it.id }) { item ->
            PostItem(item)
        }
    }
}

@Composable
fun PostItem(post: PostRemoteModel) {
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors().copy(
            contentColor = Color(0xFF1E1F22),
            containerColor = Color(0xFFD2D2C8)
        ),
        border = BorderStroke(1.dp, Color.Gray)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            Text(text = post.title.uppercase(), fontWeight = FontWeight.Bold, letterSpacing = 4.sp, lineHeight = 16.sp)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = post.body, lineHeight = 18.sp)
        }
    }
}
