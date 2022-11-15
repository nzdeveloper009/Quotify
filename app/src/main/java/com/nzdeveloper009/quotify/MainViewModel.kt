package com.nzdeveloper009.quotify

import android.content.Context
import androidx.lifecycle.ViewModel
import com.google.android.material.shape.ShapePath.PathQuadOperation
import com.google.gson.Gson
import java.nio.charset.Charset

class MainViewModel(private val context: Context) : ViewModel() {

    private var quoteList: Array<Quote> = emptyArray()
    private var index = 0

    init {
        quoteList = loadQuoteFromAssets()
    }

    private fun loadQuoteFromAssets(): Array<Quote> {
        val inputStream = context.assets.open("quotes.json")
        val size: Int = inputStream.available() // for get size of file
        val buffer = ByteArray(size) // Store file
        inputStream.read(buffer)
        inputStream.close()
        // convert buffer(byte array) to String
        // json encoding in UTF_8
        val json = String(buffer, Charsets.UTF_8)
        val gson = Gson()
        return gson.fromJson(json, Array<Quote>::class.java) // type casting and return

    }

    fun getQuote() = quoteList[index]

    //  fun nextQuote() = quoteList[++index]

    fun nextQuote(): Quote {
        return if (index < getSize()) {
            quoteList[++index]
        } else {
            quoteList[index]
        }
    }

    //    fun previousQuote() = quoteList[--index]

    fun previousQuote(): Quote {
        return if (index > 0) {
            quoteList[--index]
        } else {
            quoteList[index]
        }
    }

    private fun getSize(): Int {
        return quoteList.size
    }


}