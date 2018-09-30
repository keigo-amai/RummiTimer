package com.amadroid.rammytimer.controllers.setting

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.amadroid.rammytimer.R
import com.amadroid.rammytimer.databinding.FragmentSettingBinding

class SettingFragment : Fragment() {

    private lateinit var viewModel: SettingViewModel
    private lateinit var binding: FragmentSettingBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_setting, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(SettingViewModel::class.java)
        binding.viewModel = viewModel
    }

}
