package com.austin.bookscomposedemo.data.enums

import com.austin.bookscomposedemo.data.Book
import com.google.gson.annotations.SerializedName

/**
 * An enum representing a [Book]'s format.
 */
enum class BookType {
    @SerializedName("ebook")
    EBook,

    @SerializedName("physical book")
    PhysicalBook,

    @SerializedName("audiobook")
    AudioBook
}