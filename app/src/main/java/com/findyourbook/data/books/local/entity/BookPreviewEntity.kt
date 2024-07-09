package com.findyourbook.data.books.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.findyourbook.data.books.remote.model.BooksDto

@Entity("books_preview")
data class BookPreviewEntity(
    @PrimaryKey(autoGenerate = false) @ColumnInfo("book_id") val id: Long,
    @ColumnInfo("title") val title: String,
    @ColumnInfo("image") val image: String,
    @ColumnInfo("subtitle") val subtitle: String?,
    @ColumnInfo(name = "created_at") val createdAt: Long = System.currentTimeMillis(),
) {
    companion object {
        private fun fromDto(dto: BooksDto.BookPreviewDto) =
            with(dto) {
                BookPreviewEntity(id, title, image, subtitle)
            }

        fun fromDtoList(dtos: List<BooksDto.BookPreviewDto>): List<BookPreviewEntity> = dtos.map { dto -> fromDto(dto) }
    }
}
