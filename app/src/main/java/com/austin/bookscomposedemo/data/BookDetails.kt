package com.austin.bookscomposedemo.data

import java.util.Date

data class BookDetails(
    val authors: List<String>,
    val goodReadsRating: GoodReadsRating,

    // TODO deserializing chapter Json objects into a list of strings could be an interesting
    //  challenge
    val chapters: List<String>,

    val publicationDate: Date
)
