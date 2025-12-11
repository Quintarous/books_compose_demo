package com.austin.bookscomposedemo.ui.search

import androidx.annotation.DrawableRes
import java.text.SimpleDateFormat
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.austin.bookscomposedemo.data.Book
import com.austin.bookscomposedemo.data.BookDetails
import com.austin.bookscomposedemo.data.GoodReadsRating
import com.austin.bookscomposedemo.data.enums.BookType
import com.austin.bookscomposedemo.data.enums.Cover
import com.austin.bookscomposedemo.data.enums.Status
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.StarHalf
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.TextField
import com.austin.bookscomposedemo.data.Chapter
import com.austin.bookscomposedemo.ui.theme.Yellow

const val ROUTE_SEARCH_SCREEN = "search_screen"

data class SearchScreenState(
    val books: List<Book>
)

@Composable
fun SearchScreen(
    state: SearchScreenState,
    modifier: Modifier = Modifier
) {
    // TODO implement swipe to refresh
    LazyColumn(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxSize().padding(vertical = 4.dp, horizontal = 8.dp)
    ) {
        item {
            TextField(
                modifier = Modifier.padding(vertical = 4.dp).fillMaxWidth(),
                value = "",
                label = {
                    Row {
                        Icon(imageVector = Icons.Outlined.Search, contentDescription = null)
                        Text(text = "Search", modifier = Modifier.padding(horizontal = 4.dp))
                    }
                },
                shape = OutlinedTextFieldDefaults.shape,
                onValueChange = {
                    // TODO report the search term to the viewmodel as an event
                }
            )
        }

        items(state.books) { book ->
            BookItem(book = book)
        }
    }
}

@Composable
fun BookItem(
    book: Book,
    modifier: Modifier = Modifier
) {
    Card(modifier = modifier
        .padding(vertical = 4.dp)
        .fillMaxWidth()
    ) {
        Row(modifier = Modifier.padding(8.dp)) {
            @DrawableRes val coverResourceId = Cover.getCoverByName(book.coverName)?.drawable

            // If the cover name maps to a Cover enum use it's cover drawable to load the cover
            // image. Else fallback to showing a "cover not available" placeholder.
            if (coverResourceId != null) {
                Image(
                    painter = painterResource(id = coverResourceId),
                    contentDescription = book.name,
                    modifier = Modifier.padding(end = 8.dp)
                )
            } else {
                // TODO hardcode the cover image size and match it here with the placeholder
            }

            // Book metadata
            Column {
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

                Text("Name: ${book.name}", fontSize = 12.sp)
                Text("Authors: $authorsString", fontSize = 12.sp)
                Text("Description: ${book.description}", fontSize = 12.sp)
                Text("Chapters: ${book.details.chapters.size}", fontSize = 12.sp)
                Text("Publication Date: ${book.details.publicationDate}", fontSize = 12.sp)
                GoodReadsRating(book.details.goodreadsRating)
            }
        }
    }
}

@Composable
fun GoodReadsRating(
    goodReadsRating: GoodReadsRating,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        // Review stars
        var starCountRating: Double = goodReadsRating.rating
        while(starCountRating > 0) {
            if (starCountRating > 0.66) {
                // Add a full star
                Icon(
                    imageVector = Icons.Outlined.Star,
                    contentDescription = null, tint = Yellow,
                    modifier = Modifier.height(14.dp)
                )
            } else if (starCountRating >= 0.33) {
                // Add a half star
                Icon(
                    imageVector = Icons.AutoMirrored.Outlined.StarHalf,
                    contentDescription = null, tint = Yellow,
                    modifier = Modifier.height(14.dp)
                )
            }
            // Decrement our copy of rating for the next iteration
            starCountRating -= 1
        }

        // Rating
        Text(
            "%.1f/5.0".format(goodReadsRating.rating),
            modifier = Modifier.padding(horizontal = 8.dp),
            fontSize = 12.sp
        )
        // Review count
        Text("(${goodReadsRating.count})", fontSize = 12.sp)
    }
}

@Preview(showBackground = true, heightDp = 340, widthDp = 320)
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
                goodreadsRating = GoodReadsRating(rating = 3.14, count = 3),
                chapters = listOf(Chapter("Chapter 1")),
                publicationDate = "2022-01-08"
            ),
            coverName = "cover-isla-del-tesoro",
            description = "description"
        ),

        Book(
            id = "1",
            name = "name",
            status = Status.Active,
            bookType = BookType.PhysicalBook,
            supplier = "supplier",
            details = BookDetails(
                authors = listOf("author"),
                goodreadsRating = GoodReadsRating(rating = 3.14, count = 3),
                chapters = listOf(Chapter("Chapter 1")),
                publicationDate = "2022-01-08"
            ),
            coverName = "cover-isla-del-tesoro",
            description = "description"
        )
    )))
}

@Preview(showBackground = true, heightDp = 50, widthDp = 320)
@Composable
fun GoodReadsRatingPreview() {
    GoodReadsRating(
        GoodReadsRating(
            rating = 4.65,
            count = 69
        )
    )
}
