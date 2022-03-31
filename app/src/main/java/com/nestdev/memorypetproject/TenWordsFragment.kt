package com.nestdev.memorypetproject

import android.os.Bundle
import android.widget.TableLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider

class TenWordsFragment : Fragment(R.layout.fragment_ten_words) {
    private lateinit var viewModel: TenWordsViewModel
    private lateinit var tableLayout: TableLayout
    private lateinit var wordsArray: List<String>
    private lateinit var btnsArray: List<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(TenWordsViewModel::class.java)
    }
}