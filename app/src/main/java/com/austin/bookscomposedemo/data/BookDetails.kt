package com.austin.bookscomposedemo.data

data class BookDetails(
    val authors: List<String>,
    val goodreadsRating: GoodReadsRating,
    val chapters: List<Chapter>,
    val publicationDate: String
)

data class Chapter(
    val title: String
)
