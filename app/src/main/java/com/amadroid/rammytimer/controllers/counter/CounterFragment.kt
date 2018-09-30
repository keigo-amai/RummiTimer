package com.amadroid.rammytimer.controllers.counter

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.amadroid.rammytimer.R
import com.amadroid.rammytimer.databinding.FragmentCounterBinding

/**
 * Created by Keigo Amai on 2018/09/30.
 */
class CounterFragment: Fragment() {

    private lateinit var binding: FragmentCounterBinding
    private lateinit var viewModel: CounterViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_counter, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(CounterViewModel::class.java)
        viewModel.showToastAction = this::showToast
        viewModel.timeObservable.observe(this, Observer {
            binding.counterView.text = it
        })
        binding.viewModel = viewModel
    }

    private fun showToast(message: String) {
        view?.let {
            Snackbar.make(it, message, Snackbar.LENGTH_SHORT).show()
        }
    }

    override fun onStart() {
        super.onStart()
        viewModel.resetCountDown()
    }

    override fun onStop() {
        super.onStop()
        viewModel.stopCountDown()
    }
}