package com.austin.bookscomposedemo.data

import com.austin.bookscomposedemo.data.enums.Status
import com.austin.bookscomposedemo.data.enums.BookType
import com.google.gson.annotations.SerializedName

/**
 * A data class object representing a Book and all of it's associated meta information.
 */
data class Book(
    val id: String,
    val name: String,
    val status: Status,

    @SerializedName("type")
    val bookType: BookType,

    val supplier: String,
    val details: BookDetails,

    @SerializedName("cover")
    val coverName: String,

    val description: String
)