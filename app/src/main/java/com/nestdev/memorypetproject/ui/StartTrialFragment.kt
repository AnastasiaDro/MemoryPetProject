package com.nestdev.memorypetproject.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.nestdev.memorypetproject.databinding.FragmentStartTrialBinding
import com.nestdev.memorypetproject.ui.viewModels.StartTrialViewModel

class StartTrialFragment : Fragment() {
    private val viewModel by viewModels<StartTrialViewModel>()

    private var _binding: FragmentStartTrialBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentStartTrialBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    private fun initButtons() {
        with(binding) {
            startTrialBtn.setOnClickListener {

            }
        }
    }

}