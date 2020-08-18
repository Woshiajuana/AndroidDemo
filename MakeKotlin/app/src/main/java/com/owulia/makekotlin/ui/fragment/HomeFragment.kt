package com.owulia.makekotlin.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.owulia.makekotlin.R

/**
 * A simple [Fragment] subclass.
 */
class HomeFragment : Fragment() {

    companion object {
        val instant: HomeFragment by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) { HomeFragment() }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

}
