package com.nestdev.memorypetproject

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TableLayout
import android.widget.TableRow
import androidx.fragment.app.Fragment

import androidx.fragment.app.viewModels
import com.nestdev.memorypetproject.databinding.FragmentTenWordsBinding

class TenWordsFragment : Fragment() {
    private val viewModel by viewModels<TenWordsViewModel>()

    private lateinit var trialTable: TableLayout
    private var _binding: FragmentTenWordsBinding? = null
    private val binding get() = _binding!!
    private lateinit var tableRowList: MutableList<TableRow>
    private var counter = 0
    private var currentWordIndex = -1
    private lateinit var wordsArray: List<String>
    private lateinit var btnsArray: List<Button>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        trialTable = TableLayout(this.requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTenWordsBinding.inflate(inflater, container, false)
        return binding.root
    }


    companion object {
        fun create() = TenWordsFragment()
    }
}