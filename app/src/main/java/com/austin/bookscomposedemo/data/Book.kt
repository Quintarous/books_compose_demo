package com.austin.bookscomposedemo.data

import com.austin.bookscomposedemo.data.enums.Status
import com.austin.bookscomposedemo.data.enums.Type

/**
 * A data class object representing a Book and all of it's associated meta information.
 */
data class Book(
    val id: String,
    val name: String,
    val status: Status,
    val type: Type,
    val supplier: String,
    val details: BookDetails,
    val cover: String,
    val description: String
)