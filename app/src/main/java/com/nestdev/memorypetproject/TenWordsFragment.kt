package com.nestdev.memorypetproject

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.fragment.app.Fragment

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.nestdev.memorypetproject.databinding.FragmentTenWordsBinding
import kotlinx.coroutines.launch

class TenWordsFragment : Fragment() {
    private val viewModel by viewModels<TenWordsViewModel>()

    private lateinit var trialTable: TableLayout
    private var _binding: FragmentTenWordsBinding? = null
    private val binding get() = _binding!!
    private lateinit var tableRowList: MutableList<TableRow>
    private var counter = 0
    private var trialCounter = 0
    private var currentWordIndex = -1
    private lateinit var currentTextView: TextView
    private lateinit var finishTrialBtn: Button

    /**
     * Слова
     */
    private lateinit var wordsArray: List<String>
    private lateinit var wordsTextViewArray: Array<TextView>

    /**
     * textView для проб
     */
    private lateinit var tiral0Array : Array<TextView>
    private lateinit var tiral1Array: Array<TextView>
    private lateinit var tiral2Array: Array<TextView>
    private lateinit var tiral3Array: Array<TextView>

    /**
     * Отсроченное воспроизведение
     */
    private lateinit var deferredTrialArray: Array<TextView>

    /**
     * Массив строк всех проб
     */
    private lateinit var allTrialsArray: Array<Array<TextView>>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        trialTable = TableLayout(this.requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTenWordsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initButtons()
        initTextViewArrays()
        setTableClickListeners()
        makeCurrentClickable(trialCounter)
        val unCheckedWord = getString(R.string.forgotten_word)
        viewModel.counterData.observe(viewLifecycleOwner) {
            if (currentTextView.text == unCheckedWord) {
                currentTextView.text = it.toString()
            } else {
                currentTextView.text = unCheckedWord
                println(viewModel.counter)
                viewModel.counter-=2
                println(viewModel.counter)
            }
        }

        lifecycleScope.launch {
            viewModel.isCursorReadyFlow.collect {
                if (it) {
                    //TODO: rowNumber по умолчанию будет ноль, но в меню можно будет менять список
                    viewModel.getWordsSet(1)
                }
            }
        }
        lifecycleScope.launch {
            viewModel.listWordsFlow.collect {
                updateWordsSet(it)
            }
        }
        viewModel.getWordsCursor(view.context)
    }

    private fun updateWordsSet(wordsArray: MutableList<String?>?) {
        for (index in wordsTextViewArray.indices) {
            wordsTextViewArray[index].text = wordsArray?.get(index) ?: "null"
        }
    }

    private fun initTextViewArrays() {
        with(binding) {
            wordsTextViewArray = arrayOf(word0, word1, word2, word3, word4, word5, word6, word7, word8, word9)
            tiral0Array = arrayOf(trial00, trial01, trial02, trial03, trial04, trial05, trial06, trial07, trial08, trial09)
            tiral1Array = arrayOf(trial10, trial11, trial12, trial13, trial14, trial15, trial16, trial17, trial18, trial19)
            tiral2Array = arrayOf(trial20, trial21, trial22, trial23, trial24, trial25, trial26, trial27, trial28, trial29)
            tiral3Array = arrayOf(trial30, trial31, trial32, trial33, trial34, trial35, trial36, trial37, trial38, trial39)
            deferredTrialArray = arrayOf(deferred0, deferred1, deferred2, deferred3, deferred4, deferred5, deferred6, deferred7, deferred8, deferred9)
            allTrialsArray = arrayOf(tiral0Array, tiral1Array, tiral2Array, tiral3Array, deferredTrialArray)
        }
    }

    private fun makeCurrentClickable(trialNum: Int) {
        if (trialNum < 5) {
            if (trialNum > 0)
                allTrialsArray[trialNum - 1].forEach { it.isClickable = false }
            allTrialsArray[trialNum].forEach { it.isClickable = true }
        }
    }

    private fun setTableClickListeners() {
        allTrialsArray.forEach { setClickListenerToArrayElems(it) }
    }

    private fun initButtons() {
        with(binding) {
            finishStringBtn.setOnClickListener {
                makeCurrentClickable(trialCounter)
                trialCounter++
                viewModel.finishTrial()
            }
        }

    }

    private fun setClickListenerToArrayElems(arr: Array<TextView>) {
        arr.forEach {  it ->
            it.setOnClickListener {
                currentTextView = it as TextView
                viewModel.onTextViewPressed() }
            it.isClickable = false
        }
    }

    companion object {
        fun create() = TenWordsFragment()
    }
}