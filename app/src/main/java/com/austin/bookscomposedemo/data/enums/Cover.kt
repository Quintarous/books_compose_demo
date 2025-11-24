package com.austin.bookscomposedemo.data.enums

import androidx.annotation.DrawableRes
import com.austin.bookscomposedemo.R

enum class Cover(
    val coverName: String,
    @DrawableRes val drawable: Int
) {
    LaIslaDelTesoro(
        coverName = "cover-isla-del-tesoro",
        drawable = R.drawable.cover_isla_del_tesoro
    ),
    TessOfThedUrbervilles(
        coverName = "cover-tess-of-the-durbervilles",
        drawable = R.drawable.cover_tess_of_the_durbervilles
    );

    companion object {
        fun getCoverByName(name: String): Cover? =
            Cover.entries.find { it.coverName == name }
    }
}