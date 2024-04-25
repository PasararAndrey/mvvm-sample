package com.example.mvvmsample.ui.screen.bookdetails

import com.example.mvvmsample.ui.screen.bookdetails.model.BookDetailsUI
import javax.annotation.concurrent.Immutable

@Immutable
data class BookDetailsUIState(
    val book: BookDetailsUI,
    val isLoading: Boolean = true,
)

