package com.darek.kbembed.ui.screens.drafts

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.darek.kbcore.KBCore
import com.darek.kbembed.utils.Result
import com.darek.kbembed.utils.Result.Companion.asResult
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class DraftsScreenViewModel(
    dataSource: KBCore
) : ViewModel() {

    val draftsScreenState = dataSource.userDraftsFlow
        .map { drafts -> drafts.ifEmpty { listOf() } }
        .asResult(onErrorMessage = "Error when downloading drafts")
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(),
            Result.Loading
        )

}
