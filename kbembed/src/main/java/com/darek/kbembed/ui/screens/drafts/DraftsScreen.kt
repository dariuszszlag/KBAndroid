package com.darek.kbembed.ui.screens.drafts

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.darek.kbcore.feature.draft.Draft
import com.darek.kbembed.ui.components.PostOrDraftView
import com.darek.kbembed.utils.Result
import com.darek.kbembed.utils.Result.Companion.show

@Composable
fun DraftsScreen(
    draftsScreenState: Result<List<Draft>>,
    onGoBack: () -> Unit
) {

    draftsScreenState.show {
        LazyColumn(
            modifier = Modifier
                .padding(3.dp)
                .fillMaxSize()
        ) {
            items(it) {
                DraftView(it)
            }
        }
    }

    BackHandler {
        onGoBack()
    }

}

@Composable
private fun DraftView(draft: Draft) {
    PostOrDraftView(
        draft.id,
        "draft",
        draft.content
    )
}