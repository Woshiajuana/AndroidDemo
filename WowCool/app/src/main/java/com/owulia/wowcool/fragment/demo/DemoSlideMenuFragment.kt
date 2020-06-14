package com.owulia.wowcool.fragment.demo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.owulia.wowcool.R

/**
 * A simple [Fragment] subclass.
 */
class DemoSlideMenuFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_demo_slide_menu, container, false)
    }

}