package com.findyourbook.ui.screen.favorite.model

import com.findyourbook.model.BookModel

data class FavoriteUI(
    val id: Int,
    val title: String? = null,
    val image: String? = null,
    val subtitle: String? = null,
) {
    companion object {
        fun fromModel(model: BookModel): FavoriteUI =
            with(model) {
                FavoriteUI(
                    id = id,
                    title = title,
                    image = image,
                    subtitle = subtitle,
                )
            }
    }
}
