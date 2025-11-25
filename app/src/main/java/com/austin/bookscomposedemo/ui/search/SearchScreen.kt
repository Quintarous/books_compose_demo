package com.austin.bookscomposedemo.ui.search

import java.text.SimpleDateFormat
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.austin.bookscomposedemo.data.Book
import com.austin.bookscomposedemo.data.BookDetails
import com.austin.bookscomposedemo.data.GoodReadsRating
import com.austin.bookscomposedemo.data.enums.BookType
import com.austin.bookscomposedemo.data.enums.Cover
import com.austin.bookscomposedemo.data.enums.Status
import java.util.Date

const val ROUTE_SEARCH_SCREEN = "search_screen"

@Composable
fun SearchScreen(
    state: SearchScreenState,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxSize()
    ) {
        // TODO search bar goes on top here

        // Lazy column to display the full book list
        BookList(
            books = state.books
        )
    }
}

@Composable
fun BookList(
    books: List<Book>,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxSize()
    ) {
        items(books) { book ->
            BookItem(book = book)
        }
    }
}

@Composable
fun BookItem(
    book: Book,
    modifier: Modifier = Modifier
) {
    Card(modifier = modifier.padding(4.dp)) {
        Row(modifier = Modifier.padding(16.dp)) {
            val coverResourceId = Cover.getCoverByName(book.coverName)?.drawable

            // If the cover name maps to a Cover enum use it's cover drawable to load the cover
            // image. Else fallback to showing a "cover not available" placeholder.
            if (coverResourceId != null) {
                Image(
                    painter = painterResource(id = coverResourceId),
                    contentDescription = book.name
                )
            } else {
                // TODO hardcode the cover image size and match it here with the placeholder
            }

            Column {
                Text("ID: ${book.id}")
                Text("Name: ${book.name}")

                // Authors
                val authorsList = book.details.authors
                val authorsString = StringBuilder()
                for (i in (0 .. (authorsList.size - 1))) {
                    val author: String = authorsList[i]
                    authorsString.append(author)

                    // Add a comma after every author except for the last one
                    if (i < (authorsList.size - 1)) {
                        authorsString.append(", ")
                    }
                }
                Text("Authors: $authorsString")

                Text("Status: ${book.status}")
                Text("Description: ${book.description}")
                Text("Type: ${book.bookType}")
                Text("Supplier: ${book.supplier}")
                // TODO create a GoodReadsRating composable
                Text("Chapters: ${book.details.chapters.size}")
                Text("Publication Date: ${
                    SimpleDateFormat.getDateInstance().format(book.details.publicationDate)
                }")
            }
        }
    }
}

data class SearchScreenState(
    val books: List<Book>
)

@Preview(showBackground = true, heightDp = 320, widthDp = 320)
@Composable
fun SearchScreenPreview() {
    SearchScreen(SearchScreenState(listOf<Book>(
        Book(
            id = "1",
            name = "name",
            status = Status.Active,
            bookType = BookType.PhysicalBook,
            supplier = "supplier",
            details = BookDetails(
                authors = listOf("author"),
                goodReadsRating = GoodReadsRating(rating = 3.14, count = 3),
                chapters = listOf("chapter1", "chapter2", "chapter3"),
                publicationDate = Date()
            ),
            coverName = "cover-isla-del-tesoro",
            description = "description"
        )
    )))
}
