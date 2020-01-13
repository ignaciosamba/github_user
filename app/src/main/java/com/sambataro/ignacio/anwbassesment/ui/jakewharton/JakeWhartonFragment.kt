package com.sambataro.ignacio.anwbassesment.ui.jakewharton

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.sambataro.ignacio.anwbassesment.R

class JakeWhartonFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.jakewharton_fragment, container, false)
    }
}