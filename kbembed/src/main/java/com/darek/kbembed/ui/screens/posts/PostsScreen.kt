package com.darek.kbembed.ui.screens.posts

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.darek.kbcore.feature.post.Post
import com.darek.kbembed.ui.components.PostOrDraftView
import com.darek.kbembed.utils.Result
import com.darek.kbembed.utils.Result.Companion.show

@Composable
fun PostsScreen(
    postScreenState: Result<List<Post>>,
    onGoBack: () -> Unit
) {

    postScreenState.show {
        LazyColumn(
            modifier = Modifier
                .padding(3.dp)
                .fillMaxSize()
        ) {
            items(it) {
                PostView(it)
            }
        }
    }

    BackHandler {
        onGoBack()
    }

}

@Composable
private fun PostView(post: Post) {
    PostOrDraftView(
        post.id,
        "post",
        post.content
    )
}