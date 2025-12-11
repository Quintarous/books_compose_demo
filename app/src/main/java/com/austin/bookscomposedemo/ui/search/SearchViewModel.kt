package com.austin.bookscomposedemo.ui.search

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import com.austin.bookscomposedemo.R
import com.austin.bookscomposedemo.data.Book
import com.austin.bookscomposedemo.data.GsonHelper
import com.austin.bookscomposedemo.data.readRawResourceToString
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import com.austin.bookscomposedemo.data.Result

@HiltViewModel
class SearchViewModel @Inject constructor(
    @ApplicationContext private val context: Context
) : ViewModel() {

    /**
     * Reads the books_information.json file in the app's resources and deserializes it into a list
     * of [Book]. Which is then placed in a [SearchScreenState] and returned.
     * @return A Result with either the exception that occurred or a successfully hydrated
     * [SearchScreenState].
     */
    fun getSearchScreenState(): Result<SearchScreenState> {
        // TODO this data loading operation should be done in a coroutine!

        val booksJson: Result<String> =
            context.readRawResourceToString(R.raw.books_information)

        when (booksJson) {
            is Result.Success -> {
                // Attempt to deserialize the books list json String
                val booksList: Result<List<Book>> =
                    GsonHelper.deserializeBooksJson(booksJson.data)

                return if (booksList is Result.Success) {
                    // Happy path
                    Result.Success(SearchScreenState(booksList.data))
                } else {
                    // Gson returns Result.Error for all exceptions so this should be a safe cast
                    booksList as Result.Error
                    Log.i(TAG, "Failed to deserialize books_information.json with error: " +
                            "${booksList.exception}")
                    booksList
                }
            }

            is Result.Error -> {
                Log.i(TAG, "Failed to read books_information.json with error: ${booksJson
                    .exception.message}")
                return booksJson
            }

            else -> {
                // This shouldn't be possible in theory
                Log.i(TAG, "context.readRawResourceToString did not return a Result for " +
                        "some reason. Perhaps an exception is not being properly caught?")
                return Result.Error(Exception("Uncaught Exception"))
            }
        }
    }

    companion object {
        const val TAG = "SearchViewModel"
    }
}