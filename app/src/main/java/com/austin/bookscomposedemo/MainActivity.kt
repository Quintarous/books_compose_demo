package com.austin.bookscomposedemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.austin.bookscomposedemo.data.Book
import com.austin.bookscomposedemo.data.BookDetails
import com.austin.bookscomposedemo.data.GoodReadsRating
import com.austin.bookscomposedemo.data.enums.BookType
import com.austin.bookscomposedemo.data.enums.Status
import com.austin.bookscomposedemo.ui.search.ROUTE_SEARCH_SCREEN
import com.austin.bookscomposedemo.ui.search.SearchScreen
import com.austin.bookscomposedemo.ui.search.SearchScreenState
import com.austin.bookscomposedemo.ui.theme.BooksComposeDemoTheme
import java.util.Date

import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navHostController = rememberNavController()

            BooksComposeDemoTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MainNavHost(
                        navController = navHostController,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun MainNavHost(
    navController: NavHostController,
    modifier: Modifier
) {
    NavHost(
        navController = navController,
        startDestination = ROUTE_SEARCH_SCREEN, // TODO
        modifier = modifier
    ) {
        // Search Screen
        composable(route = ROUTE_SEARCH_SCREEN) {
            // TODO pass in real data
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
    }
}