package com.nestdev.memorypetproject

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
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
    private lateinit var currentTextView: TextView

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
        initTextViewArrays(view)
        setClickListeners()
        viewModel.counterData.observe(viewLifecycleOwner) {
            currentTextView.text = it.toString()
        }
    }

    private fun initTextViewArrays(view: View) {
        with(binding) {
            wordsTextViewArray = arrayOf(word0, word1, word2, word3, word4, word5, word6, word7, word8, word9)
            tiral0Array = arrayOf(trial00, trial01, trial02, trial03, trial04, trial05, trial06, trial07, trial08, trial09)
            tiral1Array = arrayOf(trial10, trial11, trial12, trial13, trial14, trial15, trial16, trial17, trial18, trial19)
            tiral2Array = arrayOf(trial20, trial21, trial22, trial23, trial24, trial25, trial26, trial27, trial28, trial29)
            tiral3Array = arrayOf(trial30, trial31, trial32, trial33, trial34, trial35, trial36, trial37, trial38, trial39)
            deferredTrialArray = arrayOf(deferred0, deferred1, deferred2, deferred3, deferred4, deferred5, deferred6, deferred7, deferred8, deferred9)
        }
    }

    private fun setClickListeners() {
        setClickListenerToArrayElems(tiral0Array)
        setClickListenerToArrayElems(tiral1Array)
        setClickListenerToArrayElems(tiral2Array)
        setClickListenerToArrayElems(tiral3Array)
        setClickListenerToArrayElems(deferredTrialArray)
    }

    private fun setClickListenerToArrayElems(arr: Array<TextView>) {
        arr.forEach {  it ->
            it.setOnClickListener {
                currentTextView = it as TextView
                viewModel.onTextViewPressed() }
        }
    }



    companion object {
        fun create() = TenWordsFragment()
    }
}