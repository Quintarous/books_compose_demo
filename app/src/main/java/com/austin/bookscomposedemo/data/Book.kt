package com.austin.bookscomposedemo.data

import com.austin.bookscomposedemo.data.enums.Status
import com.austin.bookscomposedemo.data.enums.BookType

/**
 * A data class object representing a Book and all of it's associated meta information.
 */
data class Book(
    val id: String,
    val name: String,
    val status: Status,
    val bookType: BookType,
    val supplier: String,
    val details: BookDetails,
    val coverName: String,
    val description: String
)