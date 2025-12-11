package com.austin.bookscomposedemo.data.enums

import com.google.gson.annotations.SerializedName

/**
 * An enum representing the status of a given [com.austin.bookscomposedemo.data.Book].
 */
enum class Status {
    @SerializedName("active")
    Active,

    @SerializedName("inactive")
    Inactive
}