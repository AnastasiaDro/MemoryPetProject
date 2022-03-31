package com.nestdev.memorypetproject

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TableLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer

class TenWordsFragment : Fragment(R.layout.fragment_ten_words) {
    private val viewModel = TenWordsViewModel by viewModels()
    private var counter = 0
    private var currentWordIndex = -1
    private lateinit var tableLayout: TableLayout
    private lateinit var wordsArray: List<String>
    private lateinit var btnsArray: List<Button>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val counterObserver = Observer<Int> { newCounter ->
            counter = newCounter
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initButtons()
    }

    fun initButtons() {
        btnsArray = listOf(R.id.)
    }

    companion object {
        fun create() = TenWordsFragment()
    }
}