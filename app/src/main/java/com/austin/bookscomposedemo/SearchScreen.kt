package com.austin.bookscomposedemo

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.austin.bookscomposedemo.data.Book
import androidx.compose.ui.Alignment

const val ROUTE_SEARCH_SCREEN = "search_screen"

@Composable
fun SearchScreen(
    state: SearchScreenState,
    modifier: Modifier = Modifier
) {
    // TODO build the actual Search Screen UI
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier.fillMaxSize()
    ) {
        Text(
            text = "Search Screen Placeholder"
        )
    }
}

data class SearchScreenState(
    val books: List<Book>
)

@Preview(showBackground = true, heightDp = 320, widthDp = 320)
@Composable
fun SearchScreenPreview() {
    SearchScreen(SearchScreenState(listOf<Book>()))
}
