package com.findyourbook.ui.screen.books

import androidx.paging.PagingData
import com.findyourbook.ui.screen.books.model.BooksUI
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

data class BooksUIState(
    val books: Flow<PagingData<BooksUI>> = flow { PagingData.empty<BooksUI>() },
)
