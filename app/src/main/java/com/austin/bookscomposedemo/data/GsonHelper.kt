package com.austin.bookscomposedemo.data

import com.google.gson.Gson
import com.google.gson.JsonArray
import com.google.gson.JsonParser

object GsonHelper {
    private val gson = Gson()

    /**
     * Takes a json list of [Book] and deserializes it into a list of [Book] objects.
     * @param json The Json String containing a list of [Book]'s.
     * @return The deserialized list of [Book]'s.
     */
    fun deserializeBooksJson(json: String): Result<List<Book>> {
        return try {
            // Use Gson's JsonParser class to parse the raw Json string into a JsonArray
            val booksArray: JsonArray = JsonParser.parseString(json).asJsonArray

            val booksList: MutableList<Book> = mutableListOf()

            booksArray.forEach { book ->
                booksList.add(gson.fromJson(book, Book::class.java))
            }

            Result.Success(booksList)
        } catch(e: Exception) {
            Result.Error(e)
        }
    }
}