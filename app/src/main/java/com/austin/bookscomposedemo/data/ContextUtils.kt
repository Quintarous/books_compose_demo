package com.austin.bookscomposedemo.data

import android.content.Context
import java.io.IOException
import java.io.InputStream

/**
 * Opens the given Raw resource file as an input stream then converts the resulting [ByteArray] to
 * a [String].
 * @param resourceId The resource id of the raw resource to be read into a String.
 * @return A [Result] containing a String if successful and an Exception if unsuccessful.
 */
fun Context.readRawResourceToString(resourceId: Int): Result<String> {
    var inputStream: InputStream? = null

    return try {
        inputStream = resources.openRawResource(resourceId)

        val bytes: ByteArray = inputStream.readBytes()

        Result.Success(String(bytes, Charsets.UTF_8))
    } catch (e: IOException) {
        Result.Error(e)
    } finally {
        try {
            inputStream?.close()
        } catch (e: IOException) {
            Result.Error(e)
        }
    }
}