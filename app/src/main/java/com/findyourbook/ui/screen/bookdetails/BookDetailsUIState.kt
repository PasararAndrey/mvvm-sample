package com.findyourbook.ui.screen.bookdetails

import com.findyourbook.ui.screen.bookdetails.model.BookDetailsUI
import javax.annotation.concurrent.Immutable

@Immutable
data class BookDetailsUIState(
    val book: BookDetailsUI = BookDetailsUI(),
    val isLoading: Boolean = false,
)
